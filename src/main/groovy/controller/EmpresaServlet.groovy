package controller

import com.google.gson.*
import model.Empresa
import service.EmpresaService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/empresas")
class EmpresaServlet extends HttpServlet {

    private final Gson gson = new Gson()
    private final EmpresaService service = new EmpresaService()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String jsonBody = req.reader.text
            Empresa novaEmpresa = gson.fromJson(jsonBody, Empresa.class)

            service.cadastrarEmpresa(novaEmpresa)

            resp.status = 201
            resp.contentType = "application/json"
            resp.writer.print('{"mensagem": "Empresa criada com sucesso!"}')
            resp.writer.flush()

        } catch (Exception e) {
            resp.status = 500
            resp.writer.print('{"erro": "' + e.message + '"}')
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.contentType = "application/json"
        resp.writer.print(gson.toJson(service.listarEmpresas()))
    }
}