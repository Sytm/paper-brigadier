package de.md5lukas.paper.brigadier.samples

import de.md5lukas.paper.brigadier.arguments.command
import de.md5lukas.paper.brigadier.arguments.multiLiteral
import de.md5lukas.paper.brigadier.arguments.player
import de.md5lukas.paper.brigadier.executors.executes
import de.md5lukas.paper.brigadier.executors.getPlayer
import org.bukkit.command.CommandSender

internal fun multiLiteralSample() {
  command("mltest") {
    multiLiteral("add", "remove") { operation ->
      player("player") {
        executes<CommandSender> { sender, context ->
          val op = operation
          val player = context.getPlayer("player")
          // Do something
        }
      }
    }
  }
}
