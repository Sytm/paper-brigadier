package de.md5lukas.paper.brigadier

import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.command.*
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

private typealias CommandExecutor<T, R> = (T, CommandContext<CommandSourceStack>) -> R

inline fun ArgumentBuilder<CommandSourceStack, *>.executors(
  unwrapProxiedCommandSender: Boolean = true,
  block: ExecutorsBuilder.() -> Unit,
) {
  this.executes(ExecutorsBuilder().also(block).build(unwrapProxiedCommandSender))
}

class ExecutorsBuilder @PublishedApi internal constructor() {

  private var proxied: CommandExecutor<ProxiedCommandSender, Int>? = null
  private var player: CommandExecutor<Player, Int>? = null
  private var entity: CommandExecutor<Entity, Int>? = null
  private var block: CommandExecutor<BlockCommandSender, Int>? = null
  private var console: CommandExecutor<CommandSender, Int>? = null
  private var any: CommandExecutor<CommandSender, Int>? = null

  @BrigadierDSL
  fun resultingProxied(block: CommandExecutor<ProxiedCommandSender, Int>) {
    proxied = block
  }

  @BrigadierDSL
  inline fun proxied(crossinline block: CommandExecutor<ProxiedCommandSender, Unit>) {
    resultingProxied { proxiedCommandSender, commandContext ->
      block(proxiedCommandSender, commandContext)
      0
    }
  }

  @BrigadierDSL
  fun resultingPlayer(block: CommandExecutor<Player, Int>) {
    player = block
  }

  @BrigadierDSL
  inline fun player(crossinline block: CommandExecutor<Player, Unit>) {
    resultingPlayer { player, commandContext ->
      block(player, commandContext)
      0
    }
  }

  @BrigadierDSL
  fun resultingEntity(block: CommandExecutor<Entity, Int>) {
    player = block
  }

  @BrigadierDSL
  inline fun entity(crossinline block: CommandExecutor<Entity, Unit>) {
    resultingEntity { entity, commandContext ->
      block(entity, commandContext)
      0
    }
  }

  @BrigadierDSL
  fun resultingConsole(block: CommandExecutor<CommandSender, Int>) {
    console = block
  }

  @BrigadierDSL
  inline fun console(crossinline block: CommandExecutor<CommandSender, Unit>) {
    resultingConsole { commandSender, commandContext ->
      block(commandSender, commandContext)
      0
    }
  }

  @BrigadierDSL
  fun resultingBlock(block: CommandExecutor<BlockCommandSender, Int>) {
    this.block = block
  }

  @BrigadierDSL
  inline fun block(crossinline block: CommandExecutor<BlockCommandSender, Unit>) {
    resultingBlock { blockCommandSender, commandContext ->
      block(blockCommandSender, commandContext)
      0
    }
  }

  @BrigadierDSL
  fun resultingAny(block: CommandExecutor<CommandSender, Int>) {
    any = block
  }

  @BrigadierDSL
  inline fun any(crossinline block: CommandExecutor<CommandSender, Unit>) {
    resultingAny { commandSender, commandContext ->
      block(commandSender, commandContext)
      0
    }
  }

  fun build(unwrapProxiedCommandSender: Boolean): Command<CommandSourceStack> =
      Executors(unwrapProxiedCommandSender, proxied, player, entity, block, console, any)
}

private class Executors(
    private val unwrapProxiedCommandSender: Boolean,
    private val proxied: CommandExecutor<ProxiedCommandSender, Int>?,
    private val player: CommandExecutor<Player, Int>?,
    private val entity: CommandExecutor<Entity, Int>?,
    private val block: CommandExecutor<BlockCommandSender, Int>?,
    private val console: CommandExecutor<CommandSender, Int>?,
    private val any: CommandExecutor<CommandSender, Int>?,
) : Command<CommandSourceStack> {
  override fun run(context: CommandContext<CommandSourceStack>): Int {
    var sender = context.source.sender

    if (sender is ProxiedCommandSender) {
      proxied?.let { proxied ->
        return proxied(sender as ProxiedCommandSender, context)
      }
      if (unwrapProxiedCommandSender) {
        sender = sender.callee
      }
    }

    return when {
      player !== null && sender is Player -> player.invoke(sender, context)
      entity !== null && sender is Entity -> entity.invoke(sender, context)
      block !== null && sender is BlockCommandSender -> block.invoke(sender, context)
      console !== null &&
          (sender is ConsoleCommandSender || sender is RemoteConsoleCommandSender) ->
          console.invoke(sender, context)
      any !== null -> any.invoke(sender, context)
      else -> -1
    }
  }
}
