/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2022 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text.minimessage;

import java.util.Collections;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Parser context for use within transformations.
 *
 * <p>This allows operating recursive parses, for cases where messages may include tokens.</p>
 *
 * @since 4.10.0
 */
@ApiStatus.NonExtendable
public interface Context {
  /**
   * Returns original message as provided to the parser.
   *
   * @return ogMessage
   * @since 4.10.0
   */
  @NotNull String originalMessage();

  /**
   * Parses a MiniMessage using all the settings of this context, including placeholders.
   *
   * @param message the message to parse
   * @return the parsed message
   * @since 4.10.0
   */
  @NotNull Component parse(final @NotNull String message);

  /**
   * Create a new parsing exception.
   *
   * @param message the detail message
   * @param tags the tag parts which caused the error
   * @return the new parsing exception
   * @since 4.10.0
   */
  @NotNull ParsingException newError(
    final @NotNull String message,
    final @NotNull List<? extends Tag.Argument> tags
  );

  /**
   * Create a new parsing exception without reference to a specific location.
   *
   * @param message the detail message
   * @return the new parsing exception
   * @since 4.10.0
   */
  default @NotNull ParsingException newError(final @NotNull String message) {
    return this.newError(message, Collections.emptyList());
  }

  /**
   * Create a new parsing exception.
   *
   * @param message the detail message
   * @param cause the cause
   * @param tags tag parts that caused the errors
   * @return the new parsing exception
   * @since 4.10.0
   */
  @NotNull ParsingException newError(
    final @NotNull String message,
    final @Nullable Throwable cause,
    final @NotNull List<? extends Tag.Argument> tags
  );
}
