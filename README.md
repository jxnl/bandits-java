# Bandit Algoriths in Java (ported from my python impl)

*from wikipedia*

In probability theory, the multi-armed bandit problem (sometimes called the K or N-armed bandit problem) is a problem in which a gambler at a row of slot machines (sometimes known as "one-armed bandits") has to decide which machines to play, how many times to play each machine and in which order to play them. When played, each machine provides a random reward from a distribution specific to that machine. The objective of the gambler is to maximize the sum of rewards earned through a sequence of lever pulls.


In practice, multi-armed bandits have been used to model the problem of managing research projects in a large organization, like a science foundation or a pharmaceutical company. Given a fixed budget, the problem is to allocate resources among the competing projects, whose properties are only partially known at the time of allocation, but which may become better understood as time passes.


In early versions of the multi-armed bandit problem, the gambler has no initial knowledge about the machines. The crucial tradeoff the gambler faces at each trial is between "exploitation" of the machine that has the highest expected payoff and "exploration" to get more information about the expected payoffs of the other machines.
