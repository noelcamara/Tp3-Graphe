set terminal wxt
set encoding utf8
set xrange [0:10500]
set yrange [0:5000]
set xlabel "Nombre de noeuds"
set ylabel "Temps en ms"

plot "dijkstraN" t "Dijkstra  Naive" with linesp lt 2 pt 2,\
 "dijkstraG" t "Dijkstra  GraphStream" with linesp lt 3 pt 3
