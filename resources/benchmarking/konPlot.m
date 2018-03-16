
	% Plotea la curva de Kinetic-over-Noise (opcionalmente muestra los errores muestrales):
	function [] = konPlot(errors)

		clc;
		grey = [0.7, 0.7, 0.7];

		konN40 = table2array(readtable('kon-N40.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));
		konN100 = table2array(readtable('kon-N100.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));
		konN400 = table2array(readtable('kon-N400.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));
		konN4000 = table2array(readtable('kon-N4000.data', 'ReadVariableNames', false, 'FileType', 'text', 'Delimiter', ' '));

		if errors == true
			skonN40 = std(konN40(:, 2:end), 0, 2);
			skonN100 = std(konN100(:, 2:end), 0, 2);
			skonN400 = std(konN400(:, 2:end), 0, 2);
			skonN4000 = std(konN4000(:, 2:end), 0, 2);
		end

		konN40 = [konN40(:, 1), mean(konN40(:, 2:end), 2)];
		konN100 = [konN100(:, 1), mean(konN100(:, 2:end), 2)];
		konN400 = [konN400(:, 1), mean(konN400(:, 2:end), 2)];
		konN4000 = [konN4000(:, 1), mean(konN4000(:, 2:end), 2)];

		figure('Name', 'Curvas de Orden Cinetico en Funcion del Ruido', 'NumberTitle', 'off');

		hold on;
		plot(konN40(:, 1), konN40(:, 2), '-o', 'MarkerSize', 10, 'Color', grey, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [0.2, 0.7, 0.9]);
		plot(konN100(:, 1), konN100(:, 2), '-o', 'MarkerSize', 10, 'Color', grey, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [0.3, 0.8, 0.5]);
		plot(konN400(:, 1), konN400(:, 2), '-o', 'MarkerSize', 10, 'Color', grey, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [1.0, 0.8, 0.0]);
		plot(konN4000(:, 1), konN4000(:, 2), '-o', 'MarkerSize', 10, 'Color', grey, 'MarkerEdgeColor', [0, 0, 0], 'MarkerFaceColor', [1.0, 0.3, 0.3]);

		if errors == true
			errorbar(konN40(:, 1), konN40(:, 2), skonN40(:, 1), '-', 'Color', grey);
			errorbar(konN100(:, 1), konN100(:, 2), skonN100(:, 1), '-', 'Color', grey);
			errorbar(konN400(:, 1), konN400(:, 2), skonN400(:, 1), '-', 'Color', grey);
			errorbar(konN4000(:, 1), konN4000(:, 2), skonN4000(:, 1), '-', 'Color', grey);
		end

		% Configuración del aspecto del plot:
		title('Curvas de Orden Cinetico en Funcion del Ruido', 'FontSize', 16, 'FontWeight', 'bold', 'Color', [0, 0, 0]);
		axis([0, 6.5, 0, 1.2]);
		legend('show');
		if errors == true
			legend({'N = 40', 'N = 100', 'N = 400', 'N = 4000', 'Desviacion Estandar'}, 'Position', [0.6 0.5 0.3 0.4]);
		else
			legend({'N = 40', 'N = 100', 'N = 400', 'N = 4000'}, 'Position', [0.6 0.5 0.3 0.4]);
		end
		xlabel('Amplitud de Ruido', 'FontSize', 16, 'FontWeight', 'bold', 'Color', [0, 0, 0]);
		ylabel('Orden Cinetico', 'FontSize', 16, 'FontWeight', 'bold', 'Color', [0, 0, 0]);
		grid on;
	end
