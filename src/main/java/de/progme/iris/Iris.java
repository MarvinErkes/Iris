/*
 * Copyright (c) 2016 "Marvin Erkes"
 *
 * This file is part of Iris.
 *
 * Iris is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.progme.iris;

import de.progme.iris.config.Header;
import de.progme.iris.config.Key;
import de.progme.iris.config.Value;
import de.progme.iris.exception.IrisException;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by Marvin Erkes on 17.06.2016.
 */
public final class Iris {

    private Iris() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new iris config from the given file name or file path.
     *
     * @param file The file name or path.
     * @return A new iris config.
     * @throws IrisException If something went wrong during loading or parsing.
     */
    public static IrisBuilder from(String file) throws IrisException {

        return from(new File(file));
    }

    /**
     * Creates a new iris config from the given path.
     *
     * @param path The path.
     * @return A new iris config.
     * @throws IrisException If something went wrong during loading or parsing.
     */
    public static IrisBuilder from(Path path) throws IrisException {

        return from(path.toFile());
    }

    /**
     * Creates a new iris config from the given uri.
     *
     * Useful if you want to load a file in the resources.
     *
     * @param uri The uri.
     * @return A new iris config.
     * @throws IrisException If something went wrong during loading or parsing.
     */
    public static IrisBuilder from(URI uri) throws IrisException {

        return from(new File(uri));
    }

    /**
     * Creates a new iris config from the given file.
     *
     * @param file The file.
     * @return A new iris config.
     * @throws IrisException If something went wrong during loading or parsing.
     */
    public static IrisBuilder from(File file) throws IrisException {

        return new IrisBuilder(file);
    }

    /**
     * A builder to add defaults.
     */
    public static class IrisBuilder {

        /**
         * The config file.
         */
        private File file;

        /**
         * All default headers for the config.
         */
        private Map<String, Header> headers = new HashMap<>();

        /**
         * Creates a new builder for the given config file.
         * @param file The config file.
         */
        public IrisBuilder(File file) {

            this.file = file;
        }

        /**
         * Adds a default header with the given key and the values.
         *
         * @param header The default header to add.
         * @param key The default key to add.
         * @param values The default values to add.
         * @return This iris builder.
         */
        public IrisBuilder def(Header header, Key key, Value... values) {

            Header h = headers.get(header.getName());
            if (h != null) {
                Key k = h.getKey(key.getName());
                if (k != null) {
                    for (Value value : values) {
                        k.addValue(value);
                    }
                } else {
                    for (Value value : values) {
                        key.addValue(value);
                    }
                    h.addKey(key);
                }
            } else {

                for (Value value : values) {
                    key.addValue(value);
                }
                header.addKey(key);

                this.headers.put(header.getName(), header);
            }

            return this;
        }

        /**
         * Builds the iris config from this builder.
         *
         * @return The parsed iris config.
         * @throws IrisException If something went wrong during the parsing.
         */
        public IrisConfig build() throws IrisException {

            return new IrisConfig(file, this);
        }

        /**
         * Returns an unmodifiable list of the default headers.
         *
         * @return The default headers as an unmodifiable list.
         */
        public List<Header> getHeaders() {

            return Collections.unmodifiableList(new ArrayList<>(headers.values()));
        }
    }
}
