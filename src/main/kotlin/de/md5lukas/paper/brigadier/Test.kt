package de.md5lukas.paper.brigadier

import io.papermc.paper.event.server.ServerResourcesLoadEvent
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.plugin.java.JavaPlugin

class Test : JavaPlugin() {

  @EventHandler
  fun on(e: ServerResourcesLoadEvent) {
    e.commands.register(this, command("test") {
      string("name") {
        executors {
          any { sender, context ->
            val name: String = context["name"]
          }
        }
      }
      blockPos("target") {
        executors {
          any { sender, context ->
            val location: Location = context.getResolved("target")
          }
        }
      }
    })
  }
}