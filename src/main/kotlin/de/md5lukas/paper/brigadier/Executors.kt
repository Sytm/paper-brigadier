@file:Suppress("UnstableApiUsage")

package de.md5lukas.paper.brigadier

import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

private typealias CommandExecutor<S, R> = (S, CommandContext<CommandSourceStack>) -> R

@PublishedApi
internal object NoCommandProvided : Command<CommandSourceStack> {
  override fun run(context: CommandContext<CommandSourceStack>): Int {
    JavaPlugin.getProvidingPlugin(NoCommandProvided::class.java)
        .slF4JLogger
        .warn("No command executor provided for \"${context.input}\" with sender ${context.source}")
    return 0
  }
}

@BrigadierDSL
inline fun <reified S : CommandSender> ArgumentBuilder<CommandSourceStack, *>.executesResulting(
    crossinline block: CommandExecutor<S, Int>
) {
  val command = this.command ?: NoCommandProvided
  executes {
    val sender = it.source.sender

    if (sender is S) {
      block(sender, it)
    } else {
      command.run(it)
    }
  }
}

@BrigadierDSL
inline fun <reified S : CommandSender> ArgumentBuilder<CommandSourceStack, *>.executes(
    crossinline block: CommandExecutor<S, Unit>
) {
  executesResulting<S> { player, commandContext ->
    block(player, commandContext)
    Command.SINGLE_SUCCESS
  }
}
