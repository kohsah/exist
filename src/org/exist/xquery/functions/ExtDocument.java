/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-03 Wolfgang M. Meier
 *  wolfgang@exist-db.org
 *  http://exist.sourceforge.net
 *  
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *  
 *  $Id$
 */
package org.exist.xquery.functions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.exist.dom.DocumentImpl;
import org.exist.dom.DocumentSet;
import org.exist.dom.ExtArrayNodeSet;
import org.exist.dom.NodeProxy;
import org.exist.dom.NodeSet;
import org.exist.dom.QName;
import org.exist.security.PermissionDeniedException;
import org.exist.xquery.Cardinality;
import org.exist.xquery.Dependency;
import org.exist.xquery.Function;
import org.exist.xquery.FunctionSignature;
import org.exist.xquery.XQueryContext;
import org.exist.xquery.XPathException;
import org.exist.xquery.value.Item;
import org.exist.xquery.value.Sequence;
import org.exist.xquery.value.SequenceIterator;
import org.exist.xquery.value.SequenceType;
import org.exist.xquery.value.Type;

/**
 * Implements eXist's document() function.
 * 
 * This will be replaced by XQuery's fn:doc() function.
 * 
 * @author wolf
 */
public class ExtDocument extends Function {

	public final static FunctionSignature signature =
		new FunctionSignature(
			new QName("document", BUILTIN_FUNCTION_NS),
            "Includes one or more documents " +
            "into the input sequence. This function is specific to eXist and " +
            "will be replaced with the corresponding fn:doc function. Currently, " +
            "eXist interprets each argument as an absolute path pointing to a " +
            "document in the database, as for example, '/db/shakespeare/plays/hamlet.xml'. " +
            "If no arguments are specified, the function will load all documents in the " +
            "database.",
			new SequenceType[] {
				 new SequenceType(Type.STRING, Cardinality.ONE_OR_MORE)},
			new SequenceType(Type.NODE, Cardinality.ZERO_OR_MORE),
			true);

	private List cachedArgs = null;
	private Sequence cached = null;
	
	/**
	 * @param context
	 * @param signature
	 */
	public ExtDocument(XQueryContext context) {
		super(context, signature);
	}
	
	/* (non-Javadoc)
	 * @see org.exist.xpath.Function#getDependencies()
	 */
	public int getDependencies() {
		return Dependency.CONTEXT_SET;
	}
	
	/* (non-Javadoc)
	 * @see org.exist.xpath.Expression#eval(org.exist.dom.DocumentSet, org.exist.xpath.value.Sequence, org.exist.xpath.value.Item)
	 */
	public Sequence eval(
		Sequence contextSequence,
		Item contextItem)
		throws XPathException {
	    DocumentSet docs = null;
	    if (getArgumentCount() == 0) {
	        if(cached != null)
	            return cached;
	        docs = new DocumentSet();
	        context.getBroker().getAllDocuments(docs);
	    } else {
		    List args = getParameterValues(contextSequence, contextItem);
			boolean cacheIsValid = false;
			if(cachedArgs != null)
			    cacheIsValid = compareArguments(cachedArgs, args);
			if(cacheIsValid)
			    return cached;
			docs = new DocumentSet();
			for(int i = 0; i < args.size(); i++) {
				String next = (String)args.get(i);
				if(next.length() == 0)
					throw new XPathException("Invalid argument to fn:doc function: empty string is not allowed here.");
				if(next.charAt(0) != '/')
					next = context.getBaseURI() + '/' + next;
				try {
					DocumentImpl doc = (DocumentImpl) context.getBroker().getDocument(next);
					if(doc != null)
						docs.add(doc);
				} catch (PermissionDeniedException e) {
					throw new XPathException("Permission denied: unable to load document " + next);
				}
			}
			cachedArgs = args;
	    }
		NodeSet result = new ExtArrayNodeSet(docs.getLength(), 1);
		for (Iterator i = docs.iterator(); i.hasNext();) {
			result.add(new NodeProxy((DocumentImpl) i.next(), -1));
		}
		cached = result;
		return result;
	}
	
	private List getParameterValues(Sequence contextSequence, Item contextItem) throws XPathException {
        List args = new ArrayList(getArgumentCount() + 10);
	    for(int i = 0; i < getArgumentCount(); i++) {
	        Sequence seq =
				getArgument(i).eval(contextSequence, contextItem);
			for (SequenceIterator j = seq.iterate(); j.hasNext();) {
				Item next = j.nextItem();
				args.add(next.getStringValue());
			}
	    }
	    return args;
    }

    private boolean compareArguments(List args1, List args2) {
        if(args1.size() != args2.size())
            return false;
        for(int i = 0; i < args1.size(); i++) {
            String arg1 = (String)args1.get(i);
            String arg2 = (String)args2.get(i);
            if(!arg1.equals(arg2))
                return false;
        }
        return true;
    }
    
    
    /* (non-Javadoc)
     * @see org.exist.xquery.PathExpr#resetState()
     */
    public void resetState() {
        cached = null;
        cachedArgs = null;
    }
}
