# Module paper-brigadier

Library providing a lightweight Kotlin DSL on top of the Brigadier API provided by the Paper API.

Example command:
```kotlin
command("example") {
  literal("playersOnly") {
    requiresPlayer()
    executes<Player> { player, _ -> 
      player.sendMessagePlain("You are allowed to use this")
    }
  }
  literal("suggest") {
    greedyString("suggestion") {
      executes<CommandSender> { _, context -> 
        val suggestion: String = context["suggestion"]
        Bukkit.getConsoleSender().sendPlainMessage("Someone suggested: $suggestion")
      }
      executes<ConsoleCommandSender> { sender, context ->
        val suggestion: String = context["suggestion"]
        sender.sendPlainMessage("You suggested: $suggestion")
      }
    }
  }
  literal("greet") {
    player("to-greet") {
      suggests(SuggestionProviders.onlinePlayers(includeSelf = false))
      executes<CommandSender> { sender, context -> 
        context.getPlayer("to-greet").sendPlainMessage("${sender.name} is greeting you")
      }
    }
  }
}
```

# Package de.md5lukas.paper.brigadier.arguments

Functions required to construct the command nodes of an Brigadier command.

# Package de.md5lukas.paper.brigadier.executors

Functions for setting and writing command executors.

Examples:
```kotlin
literal("multiply") {
  integer("a") {
    integer("b") {
      executesResulting<CommandSender> { sender, context ->
        val a: Int = context["a"]
        val b: Int = context["b"]
        
        a * b
      }
    }
  }
}
literal("who") {
  executes<CommandSender> { sender, context ->
    // Everyone else will execute this command, like entities
  }
  executes<ConsoleCommandSender> { sender, context ->
    // The console will execute this command
  }
  executes<Player> { sender, context -> 
    // The player will execute this command
  }
}
player("someone") {
  executes<CommandSender> { sender, context ->
    context.getPlayer("someone").heal(5f)
  }
}
```

# Package de.md5lukas.paper.brigadier.requirements

Functions related to the requirement of Brigadier command nodes.

Examples:
```kotlin
literal("sample1") {
  requiresPermission("test.sample")
  // Allow anyone with the permission test.sample to execute this command node
}
literal("sample2") {
  requiresConsole()
  // Only allow the console to execute this command node
}
literal("sample3") {
  requiresPlayer()
  // Only allow players to execute this command node
}
literal("sample4") {
  requiresPlayer()
  requiresPermission("test.sample")
  // Only allow players with the permission test.sample to execute this command node
}
literal("sample4") {
  requiresPlayer()
  andRequires {
    val sender = it.sender as Player
    it.health >= 10.0 || it.hasPermission("test.ignoreCheck")
  }
  // Only allow players with at least 10 health or the permission test.sample to execute this command node
}
literal("sample5") {
  requires { plugin.yourConfig.sample5Enabled }
  requiresPlayer()
  // Only allow players to execute this command node when a config flag is true
}
```

# Package de.md5lukas.paper.brigadier.suggestions

Implementations of the SuggestionProvider interface for ease of use.

Examples:
```kotlin
player("someone-else") {
  suggests(SuggestionProviders.onlinePlayers(includeSelf = false))
}
greedyString("search-query") {
  // This will work with getLastSearchQueries returning an Iterable directly or in a CompletableFuture
  suggests(SuggestionProviders.dynamic { yourApi.getLastSearchQueries(it.sender) })
}
greedyString("when") {
  suggests(SuggestionProviders.static("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"))
}
greedyString("whenWithTooltips") {
  suggests(SuggestionProviders.static(
    "Monday" to Component.text("First day of the week"),
    "Tuesday" to Component.text("Second day of the week"),
    "Wednesday" to Component.text("Third day of the week"),
    "Thursday" to Component.text("Fourth day of the week"),
    "Friday" to Component.text("Fifth day of the week"),
    "Saturday" to Component.text("Sixth day of the week").color(NamedTextColor.GOLD),
    "Sunday" to Component.text("Seventh day of the week").color(NamedTextColor.GOLD),
  ))
}
```