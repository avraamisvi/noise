/* Copyright (C) 2011 Garrett Fleenor

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; either version 3.0 of the License, or (at
 your option) any later version.

 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 License (COPYING.txt) for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 This is a port of libnoise ( http://libnoise.sourceforge.net/index.html ).  Original implementation by Jason Bevins

*/

package net.royawesome.jlibnoise.module.source;

import net.royawesome.jlibnoise.Utils;
import net.royawesome.jlibnoise.module.Module;

public class Cylinders extends Module {
	public static final double DEFAULT_CYLINDERS_FREQUENCY = 1.0;

	double frequency = DEFAULT_CYLINDERS_FREQUENCY;

	public Cylinders() {
		super(0);
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	@Override
	public int GetSourceModuleCount() {
		return 0;
	}

	@Override
	public double GetValue(double x, double y, double z) {
		x *= frequency;
		z *= frequency;

		double distFromCenter = Math.sqrt(x * x + z * z);
		double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
		double distFromLargerSphere = 1.0 - distFromSmallerSphere;
		double nearestDist = Utils.GetMin(distFromSmallerSphere, distFromLargerSphere);
		return 1.0 - (nearestDist * 4.0); // Puts it in the -1.0 to +1.0 range.

	}

}