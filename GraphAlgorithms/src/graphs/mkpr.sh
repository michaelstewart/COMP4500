#!/bin/bash
a2ps-ejava \
	Vertex.java \
	Graph.java \
	DGraph.java \
	UGraph.java \
	GraphAdj.java \
	DGraphAdj.java \
	UGraphAdj.java \
	GraphMatrix.java \
	DGraphMatrix.java \
	UGraphMatrix.java \
 		> print.ps
ps2pdf print.ps
rm print.ps

