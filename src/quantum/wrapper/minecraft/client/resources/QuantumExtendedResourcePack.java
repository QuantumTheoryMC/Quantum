/*
 * The MIT License
 *
 * Copyright 2016 link.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * Created file on 12/5/16 at 9:17 PM.
 *
 * This file is part of Quantum API
 */
package quantum.wrapper.minecraft.client.resources;

import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The Quantum Extended Resource Pack is a mechanism for defining overlapping
 * resources for minecraft instead of directly modifying the default resource
 * pack implementation with mods' own resources. This is especially necessary
 * since mods can be loaded and unloaded at runtime, and given that--although
 * it
 * may be seldom that mods are loaded and unloaded--the
 * nature of Quantum allows loading and unloading, which means frequent
 * resource
 * exchanges.
 * <p>
 * Because of this mechanism, handling mod resources is significantly easier,
 * and the startup time is reduced since fewer minecraft base modifications are
 * required. When a mod is loaded, the resources are simply registered to this
 * resource pack, and when a mod is unloaded, the resources are simply removed
 * from this resource pack. The resource pack is added to the internal
 * repository and then resources are refreshed.
 * </p>
 *
 * @author link
 */
public final class QuantumExtendedResourcePack extends DefaultResourcePack implements IResourcePack {

	private static final Set<String> DOMAINS  = new HashSet<>(4, 0.9f);

	static {
		DOMAINS.addAll(defaultResourceDomains);
		DOMAINS.add("quantum");
	}

	public QuantumExtendedResourcePack(Map<String, File> p_i46346_1_) {
		super(p_i46346_1_);
	}

	public static void addDomain(String domain) {
		synchronized (DOMAINS) {
			try {
				DOMAINS.wait(0, 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DOMAINS.add(domain);
			DOMAINS.notify();
		}
	}

	public void addResource(ResourceLocation resourceLocation, IResource resource) {
	}

	public void removeResource(ResourceLocation resourceLocation) {

	}

	@Override
	public InputStream getInputStream(ResourceLocation location) throws IOException {
		// TODO quantum default resources
		return location instanceof QResourceLocation ? ((QResourceLocation) location).retrieve() : super.getInputStream(location);
	}

	@Override
	public final boolean resourceExists(ResourceLocation location) {
		if (location.getResourceDomain().contains("quantum:") && location instanceof QResourceLocation) {
			return ((QResourceLocation) location).exists();
		}

		return super.resourceExists(location);
	}

	@Override
	public final Set<String> getResourceDomains() {
		return Collections.unmodifiableSet(DOMAINS);
	}

	@Override
	public final String getPackName() {
		return "Quantum Extended Default";
	}
}
