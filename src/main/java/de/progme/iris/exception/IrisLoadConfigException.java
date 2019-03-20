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

package de.progme.iris.exception;

/**
 * Created by Marvin Erkes on 18.06.2016.
 */
public class IrisLoadConfigException extends IrisException {

    /**
     * Creates a new iris load config exception with the given message.
     *
     * @param message The message.
     */
    public IrisLoadConfigException(String message) {

        super(message);
    }
}
