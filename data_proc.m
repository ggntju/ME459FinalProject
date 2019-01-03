clear;
n_spheres = [1, 2 ,4, 8, 16, 32];
n_triangles = [12, 48, 108, 192, 300, 432, 588, 768, 972, 1200];
time_data = [4, 7, 5, 8, 14, 19; ...
             5, 8, 14, 19, 29, 51; ...
             11, 14, 21, 32, 57, 86; ...
             15, 19, 33, 59, 83, 121; ...
             18, 29, 45, 75, 109, 150; ...
             25, 37, 61, 90, 129, 172; ...
             29, 43, 77, 105, 147, 185; ...
             40, 53, 88, 134, 156, 197; ...
             41, 65, 102, 147, 162, 234; ...
             43, 67, 91, 118, 176, 250;];
         
[Smesh, Tmesh] = meshgrid(n_spheres, n_triangles);  
figure()
contourf(Smesh, Tmesh, log(time_data), 50);
c = colorbar();
set(gca,'Fontsize',18,'Fontweight','bold')
xlabel('# of spheres')
ylabel('# of triangles')
title('log(Simulation time) (Unit: ms)')

figure()
i_triangle = 7;
p1 = polyfit(log(n_spheres), log(time_data(i_triangle, :)), 1);
plot(log(n_spheres), log(time_data(i_triangle, :)), 'linewidth', 2.0)
set(gca,'Fontsize',18,'Fontweight','bold')
xlabel('log(# of spheres)')
ylabel('log(Simulation time (Unit: ms))')

figure()
i_sphere = 5;
p2 = polyfit(log(n_triangles), log(time_data(:, i_sphere))', 1);
plot(log(n_triangles), log(time_data(:, i_sphere)), 'linewidth', 2.0)
set(gca,'Fontsize',18,'Fontweight','bold')
xlabel('log(# of triangles)')
ylabel('log(Simulation time (Unit: ms))')