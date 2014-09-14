#!/bin/bash
a2ps-ejava \
	BreadthFirst.java \
	BreadthFirstTest.java \
	BreadthFirstVertex.java \
	Components.java \
	ComponentsTest.java \
	DepthFirst.java \
	DepthFirstTest.java \
	OutDegree.java \
	Topological.java \
	UniversalSink.java \
 		> print.ps
ps2pdf print.ps
rm print.ps
