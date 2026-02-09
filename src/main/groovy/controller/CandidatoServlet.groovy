package controller

import com.google.gson.*
import model.Candidato
import service.CandidatoService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@WebServlet("/candidatos")
class CandidatoServlet extends HttpServlet {


    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)) // Saída: "2023-12-31"
                }
            })
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                    return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE) // Entrada: "2023-12-31"
                }
            })
            .create()

    private final CandidatoService service = new CandidatoService()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {

            String jsonBody = req.reader.text


            Candidato novoCandidato = gson.fromJson(jsonBody, Candidato.class)


            service.cadastrarCandidato(novoCandidato)


            resp.status = 201
            resp.contentType = "application/json"
            resp.characterEncoding = "UTF-8"

            resp.writer.print('{"mensagem": "Candidato criado com sucesso!"}')
            resp.writer.flush()

        } catch (Exception e) {
            e.printStackTrace()
            resp.status = 500
            resp.writer.print('{"erro": "' + e.message + '"}')
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.contentType = "application/json"
            resp.characterEncoding = "UTF-8"


            String jsonResposta = gson.toJson(service.listarTodos())
            resp.writer.print(jsonResposta)

        } catch (Exception e) {
            resp.status = 500
            resp.writer.print('{"erro": "' + e.message + '"}')
        }
    }
}