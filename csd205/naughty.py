import math
correctLog={}
def correct(n):
	if n==1: return 0
	if n==2: return 1
	if n== 3: return 2
	numways=0
	for i in range(1, n-1):
		if n-i in correctLog.keys():
			lesscorrect=correctLog[n-i]
		else:
			lesscorrect=correct(n-i)
		numways+= math.comb(n,i)*lesscorrect
	numways+=1

	ans= math.factorial(n)-numways
	correctLog[n]=ans
	return ans
print("Naughty couples function >:)")
num=30
print("nCf(",num,") = ",correct(num),sep='')