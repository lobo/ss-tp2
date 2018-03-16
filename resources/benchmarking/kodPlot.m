
	% Plotea la curva de Kinetic-over-Density (opcionalmente muestra los errores muestrales):
	function [] = kodPlot(errors)

		clc;
		grey = [0.7, 0.7, 0.7];

		kodEta01 = table2array(readtable('kod-eta0.1.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));
		kodEta2 = table2array(readtable('kod-eta2.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));
		kodEta5 = table2array(readtable('kod-eta5.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));

		if errors == true
			skodEta01 = std(kodEta01(:, 2:end), 0, 2);
			skodEta2 = std(kodEta2(:, 2:end), 0, 2);
			skodEta5 = std(kodEta5(:, 2:end), 0, 2);
		end

		kodEta01 = [kodEta01(:, 1), mean(kodEta01(:, 2:end), 2)];
		kodEta2 = [kodEta2(:, 1), mean(kodEta2(:, 2:end), 2)];
		kodEta5 = [kodEta5(:, 1), mean(kodEta5(:, 2:end), 2)];

		figure('Name', 'Curvas de Orden Cinetico en Funcion de la Densidad', 'NumberTitle', 'off');

		hold on;
		plot(kodEta01(:, 1), kodEta01(:, 2), '-o', 'Color', grey, 'MarkerSize', 10, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [0.3, 0.8, 0.5]);
		plot(kodEta2(:, 1), kodEta2(:, 2), '-o', 'Color', grey, 'MarkerSize', 10, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [1.0, 0.8, 0.0]);
		plot(kodEta5(:, 1), kodEta5(:, 2), '-o', 'Color', grey, 'MarkerSize', 10, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [1.0, 0.3, 0.3]);

		if errors == true
			errorbar(kodEta01(:, 1), kodEta01(:, 2), skodEta01(:, 1), '-', 'Color', grey);
			errorbar(kodEta2(:, 1), kodEta2(:, 2), skodEta2(:, 1), '-', 'Color', grey);
			errorbar(kodEta5(:, 1), kodEta5(:, 2), skodEta5(:, 1), '-', 'Color', grey);
		end

		% Configuración del aspecto del plot:
		title('Curvas de Orden Cinetico en Funcion de la Densidad', 'FontSize', 16, 'FontWeight', 'bold', 'Color', [0, 0, 0]);
		axis([0, 5.5, 0, 1.2]);
		legend('show');
		if errors == true
			legend({'eta = 0.1', 'eta = 2.0', 'eta = 5.0', 'Desviacion Estandar'}, 'Position', [0.1 0.5 0.3 0.4]);
		else
			legend({'eta = 0.1', 'eta = 2.0', 'eta = 5.0'}, 'Position', [0.1 0.5 0.3 0.4]);
		end
		xlabel('Densidad', 'FontSize', 16, 'FontWeight', 'bold', 'Color', [0, 0, 0]);
		ylabel('Orden Cinetico', 'FontSize', 16, 'FontWeight', 'bold', 'Color', [0, 0, 0]);
		grid on;
	end
