import java.util.Arrays;
import java.util.Random;

public class Perceptron {
    private double[] peso;
    private double bias;
    private double entrenamiento;

    public Perceptron(int datos, double aprendizaje) {
        this.peso = new double[datos];
        this.entrenamiento = aprendizaje;

        Random random = new Random();
        for (int i = 0; i < datos; i++) {
            this.peso[i] = random.nextDouble() - 0.5;
        }
        this.bias = random.nextDouble() - 0.5;
    }

    private int stepFunction (double sum) {
        return sum >= 0 ? 1 : 0;
    }

    public int prediccion(double[] inputs) {

        double sum = bias;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * peso[i];
        }

        return stepFunction(sum);
    }

    public double train(double[] datos, int expected) {
        int prediction = prediccion(datos);
        int error = expected - prediction;

        if (error != 0) {
            for (int i = 0; i < peso.length; i++) {
                peso[i] += entrenamiento * error * datos[i];
            }
            bias += entrenamiento * error;
        }

        return error;
    }

    public void trainBatch(double[][] entradas, int[] expectativa, int maximaEntrada) {
        System.out.println("Comenzando Entrenamiento");
        System.out.println("Pesos iniciales: " + Arrays.toString(peso));
        System.out.println("Bias inicial: " + bias);

        int iteration = 0;
        boolean persisteError = true;

        while (persisteError && iteration < maximaEntrada) {
            persisteError = false;
            System.out.println("Iteración " + (iteration + 1));

            for (int i = 0; i < entradas.length; i++) {
                double[] input = entradas[i];
                int target = expectativa[i];

                int output = prediccion(input);
                int error = target - output;

                double[] oldWeights = Arrays.copyOf(peso, peso.length);
                double oldBias = bias;

                double trainError = train(input, target);

                if (trainError != 0) {
                    persisteError = true;
                }

                System.out.println("  Ejemplo " + Arrays.toString(input) + " -> Esperado: " + target + ", Obtenido: " + output);
                if (error != 0) {
                    System.out.println("Error: " + error + "\n");
                    System.out.println("Pesos anteriores: " + Arrays.toString(oldWeights));
                    System.out.println("Bias anterior: " + oldBias);
                    System.out.println("Nuevos pesos: " + Arrays.toString(peso));
                    System.out.println("Nuevo bias: " + bias);
                }
            }
            iteration++;
        }

        System.out.println("\nEntrenamiento completado en " + iteration + " iteraciones");
        System.out.println("Pesos finales: " + Arrays.toString(peso));
        System.out.println("Bias final: " + bias);
    }

    public static void main(String[] args) {

        double[][] andInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] andOutputs = {0, 0, 0, 1};

        double[][] orInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] orOutputs = {0, 1, 1, 1};

        System.out.println("ENTRENAMIENTO AND");
        Perceptron andPerceptron = new Perceptron(2, 0.1);
        andPerceptron.trainBatch(andInputs, andOutputs, 100);

        System.out.println("\nPRUEBA AND");
        for (double[] entrada : andInputs) {
            System.out.println(Arrays.toString(entrada) + " -> " + andPerceptron.prediccion(entrada));
        }

        // Entrenar OR
        System.out.println("\nENTRENAMIENTO OR");
        Perceptron orPerceptron = new Perceptron(2, 0.1);
        orPerceptron.trainBatch(orInputs, orOutputs, 100);

        // Probar OR
        System.out.println("\nPRUEBA OR");
        for (double[] entrada : orInputs) {
            System.out.println(Arrays.toString(entrada) + " -> " + orPerceptron.prediccion(entrada));
        }
    }
}