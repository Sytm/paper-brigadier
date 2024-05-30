@file:Suppress("UnstableApiUsage")

package de.md5lukas.paper.brigadier

import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.command.brigadier.argument.ArgumentTypes
import io.papermc.paper.registry.RegistryKey

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
inline fun <T> ArgumentBuilder<CommandSourceStack, *>.argument(
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
inline fun ArgumentBuilder<CommandSourceStack, *>.entity(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.entity(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.entities(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.entities(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.player(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.player(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.players(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.players(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.playerProfiles(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.playerProfiles(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.blockPosition(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.blockPosition(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.blockState(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.blockState(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.itemStack(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.itemStack(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.itemPredicate(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.itemPredicate(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.namedColor(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.namedColor(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.component(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.component(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.style(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.style(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.signedMessage(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.signedMessage(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.scoreboardDisplaySlot(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.scoreboardDisplaySlot(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.namespacedKey(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.namespacedKey(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.key(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.key(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.integerRange(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.integerRange(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.doubleRange(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.doubleRange(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.world(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.world(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.gameMode(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.gameMode(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.heightMap(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.heightMap(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.uuid(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.uuid(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.objectiveCriteria(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.objectiveCriteria(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.entityAnchor(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.entityAnchor(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.time(
    name: String,
    mintime: Int = 0,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.time(mintime), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.templateMirror(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.templateMirror(), block)

@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.templateRotation(
    name: String,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.templateRotation(), block)

@BrigadierDSL
inline fun <T> ArgumentBuilder<CommandSourceStack, *>.resource(
    name: String,
    registryKey: RegistryKey<T>,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.resource(registryKey), block)

@BrigadierDSL
inline fun <T> ArgumentBuilder<CommandSourceStack, *>.resourceKey(
    name: String,
    registryKey: RegistryKey<T>,
    block: RequiredArgumentBuilder<CommandSourceStack, *>.() -> Unit,
) = argument(name, ArgumentTypes.resourceKey(registryKey), block)
