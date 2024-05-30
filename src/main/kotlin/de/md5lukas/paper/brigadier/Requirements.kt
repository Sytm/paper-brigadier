@file:Suppress("UnstableApiUsage")

package de.md5lukas.paper.brigadier

import com.mojang.brigadier.builder.ArgumentBuilder
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.RemoteConsoleCommandSender
import org.bukkit.entity.Player

fun ArgumentBuilder<CommandSourceStack, *>.requiresConsole(allowRcon: Boolean = true) {
  val predicate = this.requirement
  requires {
    predicate.test(it) &&
        (it.sender is ConsoleCommandSender || allowRcon && it.sender is RemoteConsoleCommandSender)
  }
}

fun ArgumentBuilder<CommandSourceStack, *>.requiresPlayer() {
  val predicate = this.requirement
  requires { predicate.test(it) && it.sender is Player }
}

fun ArgumentBuilder<CommandSourceStack, *>.requiresPermission(permission: String) {
  val predicate = this.requirement
  requires { predicate.test(it) && it.sender.hasPermission(permission) }
}
