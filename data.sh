set -e -u

for i in {0..3000}
do
    low1=65
    high1=101
    rand1=$((low1 + RANDOM%(1+high1-low1)))
    
    low2=65
    high2=101
    rand2=$((low2 + RANDOM%(1+high2-low2)))
    
    low3=65
    high3=101
    rand3=$((low3 + RANDOM%(1+high3-low3)))
    
    curl -X POST "http://localhost:9003" -d '{"'id'": "'100100'","'temperature'": "'${rand1}'"}' -H "Content-Type:application/json"
    curl -X POST "http://localhost:9003" -d '{"'id'": "'100200'","'temperature'": "'${rand2}'"}' -H "Content-Type:application/json"
    curl -X POST "http://localhost:9003" -d '{"'id'": "'100300'","'temperature'": "'${rand3}'"}' -H "Content-Type:application/json"
    
    echo '{"'id'": "'100100'","'temperature'": "'${rand1}'"}'
    echo '{"'id'": "'100200'","'temperature'": "'${rand2}'"}'
    echo '{"'id'": "'100300'","'temperature'": "'${rand3}'"}'
    
    sleep 1s
done