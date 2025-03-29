import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class JobSequencingProblem {
    class Solution {

        static class Job {
            int deadline;
            int profit;
            Job(int deadline, int profit) {
                this.deadline = deadline;
                this.profit = profit;
            }
        }

        public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
            int n = deadline.length;

            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++) {
                jobs[i] = new Job(deadline[i], profit[i]);
            }

            int maxJobs = 0;
            int curTime = 1;

            Arrays.sort(jobs, (a, b) -> a.deadline - b.deadline);

            for (Job j : jobs) {
                if (j.deadline < curTime) {
                    continue;
                } else {
                    maxJobs++;
                    curTime++;
                }
            }

            PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.profit - b.profit);
            curTime = 1;
            for (Job j : jobs) {
                curTime = Math.max(curTime, j.deadline);
                pq.add(j);
                if (pq.size() > curTime) {
                    pq.poll();
                }
            }

            int maxProfit = 0;
            for (Job j : pq) {
                maxProfit += j.profit;
            }


            ArrayList<Integer> res = new ArrayList<Integer>();
            res.add(maxJobs);
            res.add(maxProfit);

            return res;
        }
    }
}
