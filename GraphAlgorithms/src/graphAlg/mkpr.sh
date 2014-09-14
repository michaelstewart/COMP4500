#!/bin/bash
a2ps-ejava \
	Partition.java \
	SpanningKruskal.java \
	SpanningKruskalTest.java \
	Indexed.java \
	PriorityQueue.java \
	SpanningPrim.java \
	SpanningPrimTest.java \
	ShortestPathDijkstra.java \
	ShortestPathDijkstraTest.java \
	ShortestPathBellmanFord.java \
	ShortestPathBellmanFordTest.java \
 		> print.ps
ps2pdf print.ps
rm print.ps
