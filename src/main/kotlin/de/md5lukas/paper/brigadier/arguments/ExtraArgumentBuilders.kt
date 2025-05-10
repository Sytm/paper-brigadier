package de.md5lukas.paper.brigadier.arguments

import com.google.common.base.Preconditions
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.md5lukas.paper.brigadier.BrigadierDSL
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Allows using a predefined set literals as an "pseudo-argument".
 *
 * @param names The names of the literal arguments that should be registered
 * @param block Code to configure the literal argument nodes
 * @throws IllegalArgumentException when [names] is empty
 * @sample de.md5lukas.paper.brigadier.samples.multiLiteralSample
 */
@Suppress("WRONG_INVOCATION_KIND")
@OptIn(ExperimentalContracts::class)
@BrigadierDSL
inline fun ArgumentBuilder<CommandSourceStack, *>.multiLiteral(
    vararg names: String,
    block: LiteralArgumentBuilder<CommandSourceStack>.(name: String) -> Unit,
): ArgumentBuilder<CommandSourceStack, *> {
  contract { callsInPlace(block, InvocationKind.AT_LEAST_ONCE) }
  Preconditions.checkArgument(names.isNotEmpty(), "At least one argument name must be specified")
  names.forEach { name -> then(Commands.literal(name).also { builder -> builder.block(name) }) }
  return this
}
