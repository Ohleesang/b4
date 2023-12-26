import java.util.Stack

/**
 * 5~30 board의 2차원 배열의 크기
 *
 * (1)(2)(3)(4)(5) : 크레인
 * [0  0  0  0  0]          [ ]
 * [0  0  A  0  C]          [ ]
 * [0  B  E  0  A]          [ ]
 * [D  B  D  D  B]          [ ]
 * [C  E  A  C  A]          [ ]
 *
 * 각 행들을 스택으로 정의한다면???
 *
 * 행과열을 바꾸어서 스택을 더욱 쉽게 넣자.
 *
 */


class Solution {

    fun transposedMatrix(board: Array<IntArray>): Array<IntArray> {
        var transBoard = Array<IntArray>(board.size, { IntArray(board.size, { 0 }) })
        for (i in board.indices) {
            for (j in board[0].indices) {
                transBoard[i][j] = board[j][i]
            }
        }
        return transBoard
    }

    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0
        val MAX_SIZE = board.size //크레인의 범위
        var boxes = mutableListOf<Stack<Int>>()
        var bracket = Stack<Int>()

        // 전치핼렬 구하여 스택에 넣는다.
        var transBox = transposedMatrix(board)
        for (box in transBox) {
            // 정렬된 행렬을 역순으로stack에 입력
            for (idx in MAX_SIZE - 1 downTo 0) {
                if (box[idx] == 0) continue

                bracket.add(box[idx])
            }
            boxes.add(bracket)
            bracket = Stack<Int>()
        }

        var ex: Int = -1
        //moves의 값을 입력받아 해당되는 스택을 pop()
        moves.forEach {
            if (boxes[it - 1].isNotEmpty()) {
                bracket.push(boxes[it - 1].pop())

                if (ex == bracket.peek()) {
                    answer++
                    answer++
                    bracket.pop()
                    bracket.pop()
                }

                if(bracket.isNotEmpty()) ex = bracket.peek()
                else ex = -1
            }
        }

        return answer
    }
}

fun main() {
    var a = Solution()
    a.solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 3),
            intArrayOf(0, 2, 5, 0, 1),
            intArrayOf(4, 2, 4, 4, 2),
            intArrayOf(3, 5, 1, 3, 1)
        ),
        intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
    )
    //4

    a.solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 4, 4, 0),
            intArrayOf(1, 2, 2, 1)
        ),
        intArrayOf(2, 3, 1, 4, 2, 3)
    )//6
}