package controller

import com.google.gson.*
import model.Vaga
import service.VagaService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/vagas")
class VagaServlet extends HttpServlet {

    private final Gson gson = new Gson()
    private final VagaService service = new VagaService()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String jsonBody = req.reader.text


            Vaga novaVaga = gson.fromJson(jsonBody, Vaga.class)

            service.cadastrarVaga(novaVaga)

            resp.status = 201
            resp.contentType = "application/json"
            resp.writer.print('{"mensagem": "Vaga criada com sucesso!"}')

        } catch (Exception e) {
            e.printStackTrace()
            resp.status = 500
            resp.writer.print('{"erro": "' + e.message + '"}')
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.contentType = "application/json"
        resp.writer.print(gson.toJson(service.listarVagas()))
    }
}