package de.md5lukas.paper.brigadier

import com.mojang.brigadier.context.CommandContext
import io.papermc.paper.command.brigadier.CommandSourceStack

inline operator fun <reified T> CommandContext<CommandSourceStack>.get(name: String): T =
  this.getArgument(name, T::class.java)

fun <T> CommandContext<CommandSourceStack>.getResolved(name: String): T =
  this.source.getResolvedArgument<T>(this, name)