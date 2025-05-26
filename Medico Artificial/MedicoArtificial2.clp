(deftemplate sintoma
    (slot nombre)
)

(deftemplate enfermedad
    (slot nombre)
    (slot mostrado (default no))
)

(deftemplate medicamento
    (slot nombre)
    (slot mostrado (default no))
)

(deftemplate analisis
    (slot nombre)
    (slot mostrado (default no))
)

(deftemplate diagnostico_realizado
    (slot hecho)
)

(defrule iniciar_sistema
    (declare (salience 1000))
    ?f <- (initial-fact)
    =>
    (retract ?f)
    (printout t crlf "Bienvenido al sistema de diagnostico medico interactivo." crlf)
    (printout t "Por favor responda 's' para si o 'n' para no a las siguientes preguntas:" crlf crlf)
)

(defrule preguntar_sintomas
    (declare (salience 900))
    (not (sintoma))
    =>
    (printout t "Tiene fiebre? (s/n): ")
    (bind ?fiebre (read))
    (if (eq ?fiebre s) then (assert (sintoma (nombre fiebre))))
    
    (printout t "Tiene tos seca? (s/n): ")
    (bind ?tos_seca (read))
    (if (eq ?tos_seca s) then (assert (sintoma (nombre tos_seca))))
    
    (printout t "Tiene tos con flema? (s/n): ")
    (bind ?tos_flema (read))
    (if (eq ?tos_flema s) then (assert (sintoma (nombre tos_con_flema))))
    
    (printout t "Tiene fatiga? (s/n): ")
    (bind ?fatiga (read))
    (if (eq ?fatiga s) then (assert (sintoma (nombre fatiga))))
    
    (printout t "Tiene dolor de cabeza? (s/n): ")
    (bind ?dolor_cabeza (read))
    (if (eq ?dolor_cabeza s) then (assert (sintoma (nombre dolor_cabeza))))
    
    (printout t "Tiene fiebre alta (mas de 39C)? (s/n): ")
    (bind ?fiebre_alta (read))
    (if (eq ?fiebre_alta s) then (assert (sintoma (nombre fiebre_alta))))
    
    (printout t "Tiene dificultad para respirar? (s/n): ")
    (bind ?dificultad (read))
    (if (eq ?dificultad s) then (assert (sintoma (nombre dificultad_respirar))))
    
    (printout t "Tiene dolor toracico? (s/n): ")
    (bind ?dolor_toracico (read))
    (if (eq ?dolor_toracico s) then (assert (sintoma (nombre dolor_toracico))))
    
    (printout t "Tiene dolor de garganta? (s/n): ")
    (bind ?dolor_garganta (read))
    (if (eq ?dolor_garganta s) then (assert (sintoma (nombre dolor_garganta))))
    
    (printout t "Tiene inflamacion ganglionar? (s/n): ")
    (bind ?inflamacion (read))
    (if (eq ?inflamacion s) then (assert (sintoma (nombre inflamacion_ganglionar))))
    
    (printout t "Perdio el olfato o gusto recientemente? (s/n): ")
    (bind ?olfato (read))
    (if (eq ?olfato s) then (assert (sintoma (nombre perdida_olfato_gusto))))
    
    (printout t "Tiene congestion nasal? (s/n): ")
    (bind ?congestion (read))
    (if (eq ?congestion s) then (assert (sintoma (nombre congestion_nasal))))
    
    (printout t "Tiene nauseas o vomitos? (s/n): ")
    (bind ?nauseas (read))
    (if (eq ?nauseas s) then (assert (sintoma (nombre nauseas_vomitos))))
    
    (printout t "Tiene diarrea? (s/n): ")
    (bind ?diarrea (read))
    (if (eq ?diarrea s) then (assert (sintoma (nombre diarrea))))
)

(defrule diagnosticar_covid
    (declare (salience 100))
    (not (diagnostico_realizado))
    (or (sintoma (nombre fiebre))
        (sintoma (nombre fiebre_alta)))
    (sintoma (nombre tos_seca))
    (sintoma (nombre perdida_olfato_gusto))
    =>
    (assert (enfermedad (nombre COVID-19)))
    (assert (medicamento (nombre antipireticos)))
    (assert (medicamento (nombre hidratacion)))
    (assert (medicamento (nombre reposo)))
    (assert (analisis (nombre prueba_PCR)))
    (assert (diagnostico_realizado (hecho si)))
)

(defrule diagnosticar_neumonia
    (declare (salience 90))
    (not (diagnostico_realizado))
    (sintoma (nombre fiebre_alta))
    (sintoma (nombre dificultad_respirar))
    (sintoma (nombre dolor_toracico))
    (or (sintoma (nombre tos_seca))
        (sintoma (nombre tos_con_flema)))
    =>
    (assert (enfermedad (nombre Neumonia)))
    (assert (medicamento (nombre antibioticos)))
    (assert (medicamento (nombre oxigeno_si_necesario)))
    (assert (analisis (nombre radiografia_pulmonar)))
    (assert (analisis (nombre hemograma)))
    (assert (diagnostico_realizado (hecho si)))
)

(defrule diagnosticar_faringitis
    (declare (salience 80))
    (not (diagnostico_realizado))
    (sintoma (nombre dolor_garganta))
    (sintoma (nombre fiebre))
    (sintoma (nombre inflamacion_ganglionar))
    (not (sintoma (nombre tos_seca)))
    =>
    (assert (enfermedad (nombre Faringitis_estreptococica)))
    (assert (medicamento (nombre antibioticos)))
    (assert (medicamento (nombre analgesicos)))
    (assert (analisis (nombre prueba_estreptococo)))
    (assert (diagnostico_realizado (hecho si)))
)

(defrule diagnosticar_gripe
    (declare (salience 70))
    (not (diagnostico_realizado))
    (sintoma (nombre fiebre))
    (sintoma (nombre tos_seca))
    (sintoma (nombre fatiga))
    (sintoma (nombre dolor_cabeza))
    (sintoma (nombre congestion_nasal))
    (not (sintoma (nombre perdida_olfato_gusto)))
    =>
    (assert (enfermedad (nombre Gripe)))
    (assert (medicamento (nombre antipireticos)))
    (assert (medicamento (nombre antigripales)))
    (assert (medicamento (nombre reposo)))
    (assert (medicamento (nombre hidratacion)))
    (assert (diagnostico_realizado (hecho si)))
)

(defrule diagnosticar_gastroenteritis
    (declare (salience 60))
    (not (diagnostico_realizado))
    (sintoma (nombre nauseas_vomitos))
    (sintoma (nombre diarrea))
    (or (sintoma (nombre fiebre))
        (sintoma (nombre dolor_cabeza)))
    =>
    (assert (enfermedad (nombre Gastroenteritis)))
    (assert (medicamento (nombre hidratacion_oral)))
    (assert (medicamento (nombre dieta_blanda)))
    (assert (medicamento (nombre probioticos)))
    (assert (analisis (nombre coprocultivo_si_persiste)))
    (assert (diagnostico_realizado (hecho si)))
)

(defrule imprimir_resultados
    (declare (salience -100))
    (diagnostico_realizado (hecho si))
    (not (resultados_mostrados))
    =>
    (assert (resultados_mostrados))
    (printout t crlf "=== RESULTADOS DEL DIAGNOSTICO ===" crlf crlf)
)

(defrule imprimir_enfermedad
    (declare (salience -200))
    ?e <- (enfermedad (nombre ?enfermedad) (mostrado no))
    =>
    (modify ?e (mostrado si))
    (printout t "Enfermedad diagnosticada: " ?enfermedad crlf crlf)
)

(defrule imprimir_medicamentos
    (declare (salience -300))
    ?m <- (medicamento (nombre ?med) (mostrado no))
    =>
    (modify ?m (mostrado si))
    (printout t "Medicamento recomendado: " ?med crlf)
)

(defrule imprimir_analisis
    (declare (salience -400))
    ?a <- (analisis (nombre ?analisis) (mostrado no))
    =>
    (modify ?a (mostrado si))
    (printout t "Analisis recomendado: " ?analisis crlf)
)

(defrule no_diagnostico
    (declare (salience -500))
    (not (diagnostico_realizado))
    (not (no_diagnostico_mostrado))
    =>
    (assert (no_diagnostico_mostrado))
    (printout t crlf "No se pudo determinar un diagnostico claro con los sintomas proporcionados." crlf)
    (printout t "Se recomienda consultar con un medico para una evaluacion mas detallada." crlf crlf)
)

(defrule finalizar_sistema
    (declare (salience -1000))
    (or (diagnostico_realizado (hecho si))
        (no_diagnostico_mostrado))
    (not (sistema_finalizado))
    =>
    (assert (sistema_finalizado))
    (printout t crlf "=== FIN DEL DIAGNOSTICO ===" crlf)
)