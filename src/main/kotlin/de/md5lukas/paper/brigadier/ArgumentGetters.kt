@file:Suppress("UnstableApiUsage")

package de.md5lukas.paper.brigadier

import com.mojang.brigadier.context.CommandContext
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.registry.TypedKey

inline operator fun <reified T> CommandContext<CommandSourceStack>.get(name: String): T =
    this.getArgument(name, T::class.java)

@Suppress("UNCHECKED_CAST")
fun <T> CommandContext<CommandSourceStack>.getResourceKey(name: String): TypedKey<T> =
    this.getArgument(name, TypedKey::class.java) as TypedKey<T>
