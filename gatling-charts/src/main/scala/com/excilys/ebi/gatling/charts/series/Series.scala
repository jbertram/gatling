/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.gatling.charts.series

import com.excilys.ebi.gatling.core.config.GatlingConfig.CONFIG_CHARTING_MAX_PLOT_PER_SERIE
import scala.collection.mutable.ArrayBuffer

class Series[X, Y](val name: String, val data: List[(X, Y)], val colors: List[String]) {

	val sample = {
		val nb = data.size
		if (nb <= CONFIG_CHARTING_MAX_PLOT_PER_SERIE)
			data
		else {
			var i = 0
			// yep, using a mutable buffer is much much much faster!!!
			(new ArrayBuffer(data.size) ++ data).filter { plot =>
				i = i + 1
				isPlotMandatory(plot) || i % (nb / CONFIG_CHARTING_MAX_PLOT_PER_SERIE) == 0
			}
		}
	}
	
	def isPlotMandatory(plot: (X, Y)) = false
}