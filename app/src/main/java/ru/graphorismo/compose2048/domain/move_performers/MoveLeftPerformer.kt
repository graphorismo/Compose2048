package ru.graphorismo.compose2048.domain.move_performers

class MoveLeftPerformer {
    fun execute(list: MutableList<MutableList<Int>>){
        for(line in list){
            var somethingWasDone = true
            while (somethingWasDone){
                somethingWasDone = false
                for (i in 1..(line.size-1)){
                    if (line[i-1]==0 && line[i]!=0){
                        line[i-1] = line[i]
                        line[i] = 0
                        somethingWasDone = true
                    }else if (line[i-1] == line[i]  && line[i-1]!=0){
                        line[i] = 0
                        line[i-1] *= 2
                        somethingWasDone = true
                    }
                }
            }
        }
    }
}