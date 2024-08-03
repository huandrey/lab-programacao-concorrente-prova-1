package SimuladorRequest.src.services;

public class AcessoResponse {
  int statusCode;
  String message;
  // Data data;

  public AcessoResponse(StatusCodeResponse status) {
    this.statusCode = status.getCode();
    this.message = status.toString();
  }
}
