/*******************************************************************************
 * Copyright 2013 Jens Breitbart, Daniel Klemm, Dennis Obermann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.kaffeeshare.server.plugins;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Plugin to handle youtube pages.
 */
public class Youtube extends BasePlugin {

	@Override
	public boolean match(URL url) {
		return match(url, "www.youtube.com/");
	}

	@Override
	protected String getDescription(Document doc) {

		String videoId = null;
		try {
			String url = getProperty(doc, "og:url");
			videoId = url.substring(url.indexOf("v=")+2, url.length());
		} catch (Exception e) {
		}

		String description = super.getDescription(doc);
		if (videoId != null) {
			description += "<br /><br /><br /><iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/"
					+ videoId
					+ "\" frameborder=\"0\" allowfullscreen></iframe>";
		}

		return description;
	}
	
	@Override
	protected String getImageUrl(Document doc) {
		return null;
	}

}
