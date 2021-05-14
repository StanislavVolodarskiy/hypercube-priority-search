.PHONY: help
help:
	@grep -v '^\.PHONY:' makefile

JFLAGS = -g -Xlint:unchecked
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  CompactPriorityQueue.java \
		  CompactSortedArrayList.java \
		  Driver.java \
		  LongHeap.java \
		  LongPriorityQueue.java \
		  LongSort.java \
		  SortedArrayList.java \
		  Values.java \
		  LongValues.java \
		  BigDecimalValues.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

.PHONY: run-driver
run-driver: classes
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver BigDecimalValues SortedArrayList
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver BigDecimalValues CompactSortedArrayList
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver BigDecimalValues CompactPriorityQueue
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver LongValues SortedArrayList
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver LongValues CompactSortedArrayList
	echo 2 2 10 20 3 1 2 3 | java -enableassertions Driver LongValues CompactPriorityQueue

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
	| time java -enableassertions -XX:MaxHeapSize=16G Driver BigDecimalValues SortedArrayList
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
	| time java -enableassertions -XX:MaxHeapSize=16G Driver BigDecimalValues CompactSortedArrayList
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
	| time java -enableassertions -XX:MaxHeapSize=16G Driver BigDecimalValues CompactPriorityQueue
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
	| time java -enableassertions -XX:MaxHeapSize=16G Driver LongValues SortedArrayList
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
	| time java -enableassertions -XX:MaxHeapSize=16G Driver LongValues CompactSortedArrayList
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
	| time java -enableassertions -XX:MaxHeapSize=16G Driver LongValues CompactPriorityQueue

.PHONY: run-driver-long
run-driver-long-4: classes
	echo \
		10                     \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver BigDecimalValues CompactPriorityQueue
	echo \
		10                     \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
		10 0 1 2 3 4 5 6 7 8 9 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver LongValues CompactPriorityQueue

.PHONY: run-driver-4-40
run-driver-4-40: classes
	echo \
		4                                                                                                                 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver BigDecimalValues CompactPriorityQueue
	echo \
		4                                                                                                                 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
		40 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 \
	| time java -enableassertions -XX:MaxHeapSize=16G Driver LongValues CompactPriorityQueue

.PHONY: run-driver-10-5
run-driver-10-5: classes
	echo \
		10          \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
	| time java -enableassertions -Xms1m Driver BigDecimalValues CompactPriorityQueue
	echo \
		10          \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
		5 1 2 3 4 5 \
	| time java -enableassertions -Xms1m Driver LongValues CompactPriorityQueue
