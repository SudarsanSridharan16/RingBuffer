## Build

1. Open the project in IntelliJ IDEA
2. Set project JDK to 11
3. Build all artifacts

## Benchmarks

1 million elements per concurrent thread are written/read.

`test` folder: write all elements _then_ read all elements  
`contention-test` folder: write and read at the same time all elements

Set IntelliJ IDEA property: `idea.no.launcher=true`  
`AbstractRingBufferTest.CONCURRENCY` is the number of concurrent producers and the number of concurrent consumers where applicable.  
If hyper-threading is not two-way or not enabled, tweak `AbstractTestThread.spreader`.