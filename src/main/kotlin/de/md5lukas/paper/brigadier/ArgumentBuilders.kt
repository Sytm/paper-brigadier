@file:Suppress("UnstableApiUsage")

package de.md5lukas.paper.brigadier

import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.command.brigadier.argument.VanillaArgumentTypes

@BrigadierDSL
inline fun command(
    name: String,
    block: LiteralArgumentBuilder<CommandSourceStack>.() -> Unit,
): LiteralCommandNode<CommandSourceStack> = Commands.literal(name).also(block).build()

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.literal(
    name: String,
    block: LiteralArgumentBuilder<CommandSourceStack>.() -> Unit,
) {
  then(Commands.literal(name).also(block))
}

@BrigadierDSL
inline fun <reified T> ArgumentBuilder<CommandSourceStack, *>.argument(
    name: String,
    type: ArgumentType<T>,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
): ArgumentBuilder<*, *> = then(Commands.argument(name, type).also(block))

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.word(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, StringArgumentType.word(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.string(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, StringArgumentType.string(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.greedyString(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, StringArgumentType.greedyString(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.boolean(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, BoolArgumentType.bool(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.integer(
    name: String,
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, IntegerArgumentType.integer(min, max), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.long(
    name: String,
    min: Long = Long.MIN_VALUE,
    max: Long = Long.MAX_VALUE,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, LongArgumentType.longArg(min, max), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.float(
    name: String,
    min: Float = -Float.MAX_VALUE,
    max: Float = Float.MAX_VALUE,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, FloatArgumentType.floatArg(min, max), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.double(
    name: String,
    min: Double = -Double.MAX_VALUE,
    max: Double = Double.MAX_VALUE,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, DoubleArgumentType.doubleArg(min, max), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.itemStack(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.itemStack(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.blockState(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.blockState(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.uuid(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.uuid(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.entity(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.entity(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.entities(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.entities(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.player(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.player(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.players(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.players(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.playerProfiles(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.playerProfiles(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.blockPos(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.blockPos(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.signedMessage(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.signedMessage(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.component(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, VanillaArgumentTypes.component(), block)
