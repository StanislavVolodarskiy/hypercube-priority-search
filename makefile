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
	echo \
		10                      \
		 1 1                    \
		 2 1 2                  \
		 3 1 2 3                \
		 4 1 2 3 4              \
		 5 1 2 3 4 5            \
		 6 1 2 3 4 5 6          \
		 7 1 2 3 4 5 6 7        \
		 8 1 2 3 4 5 6 7 8      \
		 9 1 2 3 4 5 6 7 8 9    \
		10 1 2 3 4 5 6 7 8 9 10 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver SortedArrayList
	echo \
		10                      \
		 1 1                    \
		 2 1 2                  \
		 3 1 2 3                \
		 4 1 2 3 4              \
		 5 1 2 3 4 5            \
		 6 1 2 3 4 5 6          \
		 7 1 2 3 4 5 6 7        \
		 8 1 2 3 4 5 6 7 8      \
		 9 1 2 3 4 5 6 7 8 9    \
		10 1 2 3 4 5 6 7 8 9 10 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver CompactSortedArrayList
	echo \
		10                      \
		 1 1                    \
		 2 1 2                  \
		 3 1 2 3                \
		 4 1 2 3 4              \
		 5 1 2 3 4 5            \
		 6 1 2 3 4 5 6          \
		 7 1 2 3 4 5 6 7        \
		 8 1 2 3 4 5 6 7 8      \
		 9 1 2 3 4 5 6 7 8 9    \
		10 1 2 3 4 5 6 7 8 9 10 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver CompactPriorityQueue

.PHONY: run-driver-long
run-driver-long-4: classes
	echo 10 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 | time java -enableassertions -XX:MaxHeapSize=16G Driver CompactPriorityQueue
