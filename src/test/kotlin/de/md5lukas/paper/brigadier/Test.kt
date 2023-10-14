package de.md5lukas.paper.brigadier

import de.md5lukas.paper.brigadier.v1.executors

fun test() {
  command("pouches") {
    literal("help") {
      executes {
        0
      }
    }
    literal("input") {
      literal("text") {
        word("text") { textArgument ->
          executors {
            player { player, commandContext ->

            }
            any { commandSender, commandContext ->

            }
          }
        }
      }
      literal("number") {}
    }
  }
}