/*
 * Copyright (c) 2002, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 *
 */

package sun.jvm.hotspot.asm.sparc;

import sun.jvm.hotspot.asm.*;

class V9PopcDecoder extends InstructionDecoder
              implements V9InstructionDecoder {
    Instruction decode(int instruction, SPARCInstructionFactory factory) {
        SPARCV9InstructionFactory v9factory = (SPARCV9InstructionFactory) factory;
        int rs1Num = getSourceRegister1(instruction);
        Instruction instr = null;
        // in POPC, rs1 should be zero. see page 205 - A.41 Population Count
        if (rs1Num != 0) {
            instr = v9factory.newIllegalInstruction(instruction);
        } else {
            SPARCRegister rd = SPARCRegisters.getRegister(getDestinationRegister(instruction));
            instr = v9factory.newV9PopcInstruction(getOperand2(instruction), rd);
        }
        return instr;
    }
}
