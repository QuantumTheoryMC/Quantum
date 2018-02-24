/*
 * The MIT License
 *
 *  Copyright 2017 link.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 *   Created file on $date at $time.
 *
 *   This file is part of Quantum
 */

package quantum.api.block;

import java.util.List;
import java.util.Map;

/**
 * Represents a Block variant
 *
 * @author link
 */
public abstract class Variant extends AbstractBlock {

	protected final Map<String, Block> variantMap;
	protected final List<Block> variants;
	protected final String             variantName;
	protected final int                variantIndex;

	protected Variant(Block root, String variantName, String variantID, int variantIndex) {
		super(root.getName(), variantID, root.getMod(), root.getType());
		this.variantName = variantName;
		variants = root.getVariants();
		variants.add(this);
		this.variantIndex = variantIndex;
		this.variantMap = root.getVariantMap();
		this.variantMap.put(variantName, this);

	}

	@Override
	public Map<String, Block> getVariantMap() {
		return variantMap;
	}

	@Override
	public List<Block> getVariants() {
		return variants;
	}

	@Override
	public String getVariantName() {
		return variantName;
	}

	@Override
	public int getVariantIndex() {
		return variantIndex;
	}

}
