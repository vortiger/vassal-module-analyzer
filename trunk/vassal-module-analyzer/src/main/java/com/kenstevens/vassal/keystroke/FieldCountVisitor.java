package com.kenstevens.vassal.keystroke;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

public class FieldCountVisitor implements KeyStrokeVisitor {
	Multiset<Field> fieldCount = HashMultiset.create();

	@Override
	public void visit(BuildableKeyField buildableKeyField) {
		fieldCount.add(buildableKeyField.getField());
	}
	
	public void printFieldCounts() {
		Set<Entry<Field>> entrySet = fieldCount.entrySet();
		for (Entry<Field> entry : entrySet) {
			Field field = entry.getElement();
			System.out.println(entry.getCount()+"\t"+KeyFieldMap.nameOf(field));
		}
	}

	@Override
	public void visit(List<Buildable> path,  Buildable buildable) {
	}
}
