.PHONY: help
help:
	@grep -v '^\.PHONY:' makefile

JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  CachedSum.java \
		  CompactPriorityQueue.java \
		  CompactSortedArrayList.java \
		  Driver.java \
		  LongHeap.java \
		  LongPriorityQueue.java \
		  LongSort.java \
		  SortedArrayList.java \
		  Utils.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

.PHONY: run-driver
run-driver: classes
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver SortedArrayList
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver CompactSortedArrayList
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver CompactPriorityQueue

.PHONY: run-driver-benchmark
run-driver-benchmark: classes
	echo 7 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver SortedArrayList
	echo 7 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver CompactSortedArrayList
	echo 7 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver CompactPriorityQueue

.PHONY: run-driver-long-1
run-driver-long-1: classes
	echo 8 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver SortedArrayList

.PHONY: run-driver-long-2
run-driver-long-2: classes
	echo 8 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver CompactSortedArrayList

.PHONY: run-driver-long-3
run-driver-long-3: classes
	echo 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver CompactSortedArrayList

.PHONY: run-driver-long-4
run-driver-long-4: classes
	echo 10 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver CompactPriorityQueue
