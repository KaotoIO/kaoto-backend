package io.kaoto.backend.metadata.parser.step.serverlessworkflow;

import com.fasterxml.jackson.databind.JsonNode;
import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.YamlProcessFile;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.serverlessworkflow.api.actions.Action;
import io.serverlessworkflow.api.end.End;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public final class ServerlessWorkflowParseCatalog implements StepCatalogParser {

    public ServerlessWorkflowParseCatalog() {

    }


    @Override
    public HardcodedParseCatalog getParser(final String url, final String tag) {
        return new HardcodedParseCatalog();
    }


    @Override
    public HardcodedParseCatalog getParser(final String url) {
        return new HardcodedParseCatalog();
    }

    class HardcodedParseCatalog implements ParseCatalog<Step> {

        @Override
        public CompletableFuture<List<Step>> parse() {
            var steps = new CompletableFuture<List<Step>>();
            var list = new ArrayList<Step>();
            var parameters = new ArrayList<Parameter>();
            Step s = new Step("sw-foreach", "foreach",
            icon, parameters);
            s.setKind("ServerlessWorkflow");
            s.setDescription("Serverless Workflow foreach");
            s.setGroup("Serverless Workflow");
            s.setTitle("For Each");
            list.add(s);
            parameters.add(new Parameter<String>(
                    "name",
                    "name",
                    "name",
                    "unknown",
                    "string"));
            parameters.add(new Parameter<String>(
                    "inputCollection",
                    "inputCollection",
                    "inputCollection",
                    "${ .orders }",
                    "string"));
            parameters.add(new Parameter<String>(
                    "iterationParam",
                    "iterationParam",
                    "iterationParam",
                    "singleorder",
                    "string"));
            parameters.add(new Parameter<String>(
                    "outputCollection",
                    "outputCollection",
                    "outputCollection",
                    "${ .provisionresults }",
                    "string"));
            parameters.add(new Parameter<List<Action>>(
                    "actions",
                    "actions",
                    "actions",
                    new ArrayList<>(),
                    "functionRef"));
            parameters.add(new Parameter<String>(
                    "stateDataFilter",
                    "stateDataFilter",
                    "stateDataFilter",
                    "",
                    "string"));
            parameters.add(new Parameter<End>(
                    "end",
                    "end",
                    "end",
                    null,
                    "End"));

            parameters = new ArrayList<Parameter>();
            s = new Step("sw-inject", "inject",
                    icon, parameters);
            s.setKind("ServerlessWorkflow");
            s.setDescription("Serverless Workflow inject");
            s.setGroup("Serverless Workflow");
            s.setTitle("Inject");
            list.add(s);
            parameters.add(new Parameter<JsonNode>(
                    "data",
                    "data",
                    "data",
                    null,
                    "functionRef"));
            parameters.add(new Parameter<String>(
                    "name",
                    "name",
                    "name",
                    "unknown",
                    "string"));
            parameters.add(new Parameter<End>(
                    "end",
                    "end",
                    "end",
                    null,
                    "End"));
            steps.complete(list);
            return steps;
        }

        @Override
        public void setFileVisitor(final YamlProcessFile<Step> fileVisitor) {

        }
    }

    private final String icon = "data:image/svg+xml;base64,"
            + "PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciI"
            + "HhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rI"
            + "iB3aWR0aD0iMTI4IiBoZWlnaHQ9IjEyOCI+PGRlZnM+PHJhZGlhbEdyYW"
            + "RpZW50IGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiByPSIxMTY"
            + "uNzgxIiBjeT0iMTIyLjY4NiIgY3g9IjgwLjY3MSIgaWQ9ImQiPjxzdG9wI"
            + "HN0b3AtY29sb3I9IiNmZmU5Y2EiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3A"
            + "tY29sb3I9IiNmZjk2MDAiIG9mZnNldD0iMSIvPjwvcmFkaWFsR3JhZGll"
            + "bnQ+PGxpbmVhckdyYWRpZW50IGlkPSJhIj48c3RvcCBzdG9wLWNvbG9yP"
            + "SIjYzc3NTAwIiBvZmZzZXQ9IjAiLz48c3RvcCBzdG9wLWNvbG9yPSIjZm"
            + "ZiNTRjIiBvZmZzZXQ9IjEiLz48L2xpbmVhckdyYWRpZW50PjxyYWRpYWx"
            + "HcmFkaWVudCBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgcj0i"
            + "NTMuMTUyIiBjeT0iMTM2Ljc4NCIgY3g9IjczLjQyNyIgaWQ9ImMiPjxzd"
            + "G9wIHN0b3AtY29sb3I9IiNmZmYiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3"
            + "AtY29sb3I9IiNjYTc3MDAiIG9mZnNldD0iMSIvPjwvcmFkaWFsR3JhZGl"
            + "lbnQ+PGxpbmVhckdyYWRpZW50IGdyYWRpZW50VW5pdHM9InVzZXJTcGFj"
            + "ZU9uVXNlIiB5Mj0iNTguMDgzIiB5MT0iLTMuNDE4IiB4Mj0iNjguMjUxI"
            + "iB4MT0iNjguMjUxIiBpZD0iYiI+PHN0b3Agc3RvcC1jb2xvcj0iIzUxMz"
            + "AwMCIgb2Zmc2V0PSIwIi8+PHN0b3Agc3RvcC1jb2xvcj0iI2ZmYmE1OCIg"
            + "b2Zmc2V0PSIxIi8+PC9saW5lYXJHcmFkaWVudD48bGluZWFyR3JhZGllb"
            + "nQgaWQ9ImYiPjxzdG9wIHN0b3AtY29sb3I9IiNjNzc1MDAiIG9mZnNldD"
            + "0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiNmZmI1NGMiIG9mZnNldD0iMSI"
            + "vPjwvbGluZWFyR3JhZGllbnQ+PHJhZGlhbEdyYWRpZW50IGdyYWRpZW50"
            + "VW5pdHM9InVzZXJTcGFjZU9uVXNlIiByPSIxMDMuNDMiIGN5PSItMTEuM"
            + "zc2IiBjeD0iLTExLjA0MyIgaWQ9ImciPjxzdG9wIHN0b3AtY29sb3I9Ii"
            + "NmZmYiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiNmZmNmOGI"
            + "iIG9mZnNldD0iMSIvPjwvcmFkaWFsR3JhZGllbnQ+PHJhZGlhbEdyYWRpZ"
            + "W50IGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiByPSI5NS40OT"
            + "giIGN5PSIxMjAuNTg4IiBjeD0iNjkuOTQ4IiBpZD0iZSI+PHN0b3Agc3Rv"
            + "cC1jb2xvcj0iI2ZmZiIgb2Zmc2V0PSIwIi8+PHN0b3Agc3RvcC1jb2xvcj"
            + "0iI2ZmOTYwMCIgb2Zmc2V0PSIxIi8+PC9yYWRpYWxHcmFkaWVudD48bGl"
            + "uZWFyR3JhZGllbnQgeGxpbms6aHJlZj0iI2EiIGlkPSJtIiBncmFkaWVud"
            + "FVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjYzLjI3MSIgeTE9IjM0Lj"
            + "U4NiIgeDI9IjYzLjI3MSIgeTI9IjcxLjU4NCIgZ3JhZGllbnRUcmFuc2Zv"
            + "cm09Im1hdHJpeCguNjk0MzIgMCAwIC42OTQzMiAtMy4wNDcgLTMuMTE1K"
            + "SIvPjxsaW5lYXJHcmFkaWVudCB4bGluazpocmVmPSIjYiIgaWQ9Im4iIG"
            + "dyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iNjguMjUxIiB"
            + "5MT0iLTMuNDE4IiB4Mj0iNjguMjUxIiB5Mj0iNTguMDgzIiBncmFkaWVu"
            + "dFRyYW5zZm9ybT0ibWF0cml4KC42OTQzMiAwIDAgLjY5NDMyIC0zLjA0N"
            + "yAtMy4xMTUpIi8+PHJhZGlhbEdyYWRpZW50IHhsaW5rOmhyZWY9IiNjIiB"
            + "pZD0ibyIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiIGN4PSI3M"
            + "y40MjciIGN5PSIxMzYuNzg0IiByPSI1My4xNTIiIGdyYWRpZW50VHJhbnN"
            + "mb3JtPSJtYXRyaXgoLjY5NDMyIDAgMCAuNjk0MzIgLTMuMDQ3IC0zLjExN"
            + "SkiLz48cmFkaWFsR3JhZGllbnQgeGxpbms6aHJlZj0iI2QiIGlkPSJwIiBn"
            + "cmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgY3g9IjgwLjY3MSIgY3k9"
            + "IjEyMi42ODYiIHI9IjExNi43ODEiIGdyYWRpZW50VHJhbnNmb3JtPSJtYXR"
            + "yaXgoLjY5NDMyIDAgMCAuNjk0MzIgLTMuMDQ3IC0zLjExNSkiLz48cmFkaWF"
            + "sR3JhZGllbnQgeGxpbms6aHJlZj0iI2UiIGlkPSJxIiBncmFkaWVudFVuaXRz"
            + "PSJ1c2VyU3BhY2VPblVzZSIgY3g9IjY5Ljk0OCIgY3k9IjEyMC41ODgiIHI9I"
            + "jk1LjQ5OCIgZ3JhZGllbnRUcmFuc2Zvcm09Im1hdHJpeCguNjk0MzIgMCAwIC"
            + "42OTQzMiAtMy4wNDcgLTMuMTE1KSIvPjxsaW5lYXJHcmFkaWVudCB4bGluazp"
            + "ocmVmPSIjZiIgaWQ9InIiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNl"
            + "IiB4MT0iNjMuOTk5IiB5MT0iNTkuMDg0IiB4Mj0iNjMuOTk5IiB5Mj0iMTQ1L"
            + "jA4MiIgZ3JhZGllbnRUcmFuc2Zvcm09Im1hdHJpeCguNjk0MzIgMCAwIC42OT"
            + "QzMiAtMy4wNDcgLTMuMTE1KSIvPjxyYWRpYWxHcmFkaWVudCB4bGluazpocmV"
            + "mPSIjZyIgaWQ9InMiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiBj"
            + "eD0iLTExLjA0MyIgY3k9Ii0xMS4zNzYiIHI9IjEwMy40MyIgZ3JhZGllbnRUcm"
            + "Fuc2Zvcm09Im1hdHJpeCguNjk0MzIgMCAwIC42OTQzMiAtMy4wNDcgLTMuMTE"
            + "1KSIvPjxsaW5lYXJHcmFkaWVudCB4bGluazpocmVmPSIjaCIgaWQ9InYiIGdy"
            + "YWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iNjguMjUxIiB5MT0iL"
            + "TMuNDE4IiB4Mj0iNjguMjUxIiB5Mj0iNTguMDgzIiBncmFkaWVudFRyYW5zZm"
            + "9ybT0ibWF0cml4KC42OTQzMiAwIDAgLjY5NDMyIDM2LjM0IDM5LjEyNykiLz4"
            + "8cmFkaWFsR3JhZGllbnQgeGxpbms6aHJlZj0iI2kiIGlkPSJ3IiBncmFkaWVu"
            + "dFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgY3g9IjczLjQyNyIgY3k9IjEzNi43O"
            + "DQiIHI9IjUzLjE1MiIgZ3JhZGllbnRUcmFuc2"
            + "Zvcm09Im1hdHJpeCguNjk0MzIgMCAwIC42OTQzMiAzNi4zNCAzOS4xMjcpIi"
            + "8+PHJhZGlhbEdyYWRpZW50IHhsaW5rOmhyZWY9IiNqIiBpZD0ieCIgZ3JhZGl"
            + "lbnRVbml0cz0idXNlclNwYWNlT25Vc2UiIGN4PSI4MC42NzEiIGN5PSIxMjIu"
            + "Njg2IiByPSIxMTYuNzgxIiBncmFkaWVudFRyYW5zZm9ybT0ibWF0cml4KC42O"
            + "TQzMiAwIDAgLjY5NDMyIDM2LjM0IDM5LjEyNykiLz48cmFkaWFsR3JhZGllbn"
            + "QgeGxpbms6aHJlZj0iI2siIGlkPSJ5IiBncmFkaWVudFVuaXRzPSJ1c2VyU3B"
            + "hY2VPblVzZSIgY3g9IjY5Ljk0OCIgY3k9IjEyMC41ODgiIHI9Ijk1LjQ5OCIg"
            + "Z3JhZGllbnRUcmFuc2Zvcm09Im1hdHJpeCguNjk0MzIgMCAwIC42OTQzMiAzN"
            + "i4zNCAzOS4xM"
            + "jcpIi8+PHJhZGlhbEdyYWRpZW50IHhsaW5rOmhyZWY9IiNsIiBpZD0iQSIgZ3"
            + "JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiIGN4PSItMTEuMDQzIiBjeT0i"
            + "LTExLjM3NiIgcj0iMTAzLjQzIiBncmFkaWVudFRyYW5zZm9ybT0ibWF0cml4KC"
            + "42OTQzMiAwIDAgLjY5NDMyIDM2LjM0IDM5LjEyNykiLz48cGF0dGVybiBwYXR"
            + "0ZXJuVW5pdHM9InVzZXJTcGFjZU9uVXNlIiB3aWR0aD0iODIuNzc5IiBoZWlna"
            + "HQ9IjgyLjY0MiIgcGF0dGVyblRyYW5zZm9ybT0idHJhbnNsYXRlKDUuODM1IDM"
            + "uMTE1KSIgaWQ9IkIiPjxwYXRoIGQ9Ik0zMC44NzYgMjIuM2MtMy45OTYuNzAzLT"
            + "cuMjI5IDIuNjc2LTkuMzQ5IDUuNzA1bC0uMDY2LjA5NS4wMDUuMTE4Yy4wODEgM"
            + "i4xOTIuNTg4IDQuNDQ5IDEuNTA0IDYuNzFsLjAzNi4wODRjLjM1NC44NjIuNzcg"
            + "MS43MyAxLjI0MiAyLjU4MWwuMDU1LjA5Ny43MTYgMS4yMDQuMDY1LjEwMi44My"
            + "AxLjIyOS4wODEuMTEuNzk5IDEuMDQ4LjE4OC4yMzZhMzAuMTMxIDMwLjEzMSAw"
            + "IDAgMCAyLjI0NSAyLjQ2N2MwIC4wMDIuODk1Ljg1Ljg5NS44NWwuMjMyLjIxNC"
            + "AxLjE1My45ODMuMjU1LjIuOTkyLjc2Mi4zNDguMjUzIDEuMjk1Ljg5MSAxLjQ4"
            + "My45MDIuMzQzLjE5OCAxLjQ4MS43ODcuMTg4LjA5IDEuMzY4LjYyOC4zMjYuMT"
            + "QxIDEuNS41ODYuMjc3LjA5Ny0uMDAzLS4wMDIgMS4zNTguNDQxLjI1OC4wNzYg"
            + "MS41MTMuMzkxLjMxLjA2OCAxLjQyNi4yNzcuMTI0LjAyMSAxLjUxOS4xOTguMj"
            + "k5LjAyOWMxLjAwNy4wOSAyLjAxNS4xMjIgMi45OTUuMDlsLjI2LS4wMDggMS40"
            + "NTYtLjEwNy4wOS0uMDEgMS4zNTgtLjIwMWMuMDA0LS4wMDIuMTk3LS4wMzYuMT"
            + "k3LS4wMzZsMS4zOC0uMzEzLjE0LS4wNCAxLjI1OC0uMzk4LjExLS4wMzUgMS4y"
            + "NjQtLjUyMS4xMDctLjA1Yy4wNjUtLjAzMSAxLjI2LS42NTQgMS4yNi0uNjU0bC"
            + "4xNDQtLjA3NS4wMi0uMTUyYzEuNjY2LTguMDIzLTMuMDQ2LTE3Ljc1MS0xMS40"
            + "Ni0yMy42Ni01LjY2Ny0zLjk4NC0xMi4xODItNS42OTUtMTcuODctNC42OTZ6bS"
            + "4xMi42ODJjNS41MDgtLjk2OSAxMS44MzQuNzAyIDE3LjM1NiA0LjU4MSA3LjEg"
            + "NC45ODYgMTEuNSAxMi43MzggMTEuNSAxOS43NjYgMCAxLjAzMi0uMTEgMi4wND"
            + "YtLjMwNiAzLjAzNmwtLjk5Mi41MTctLjE2NC4wNzctMS4yMTIuNDk4LS4xMDUu"
            + "MDM2LTEuMjExLjM4Mi0uMTQ3LjA0Mi0xLjMyLjMtLjE5My4wMzQtMS4zMTMuMT"
            + "k0YTMuNTQzIDMuNTQzIDAgMCAwLS4wNzkuMDFsLTEuNDE1LjEwMy0uMjUyLjAw"
            + "OWMtLjk1Mi4wMy0xLjkzMyAwLTIuOTEyLS4wODhsLS4yOS0uMDI4LTEuNDc5LS"
            + "4xOTItLjEyMy0uMDIxLTEuMzktLjI3YTM0Ljk3NSAzNC45NzUgMCAwIDAtLjMw"
            + "My0uMDY3bC0xLjQ3NC0uMzgxLS4yNTYtLjA3Ni0xLjMyNC0uNDMtLjI3LS4wOT"
            + "QtMS40NjctLjU3Mi0uMzE5LS4xMzgtMS4zMzgtLjYxNS0uMTg1LS4wOS0xLjQ1"
            + "LS43Ny0uMzEtLjE3Ny0xLjQ3OC0uOS0xLjI2OC0uODcxLS4zNDItLjI0OC0uOT"
            + "ctLjc0NS0uMjUtLjE5Ni0xLjEyOC0uOTY1LS4yMjYtLjIwNy0uODc2LS44MzEt"
            + "LjI1Ni0uMjU0YTI5LjkwNCAyOS45MDQgMCAwIDEtMS45MzgtMi4xNTZsLS4xODU"
            + "tLjIzMS0uNzgtMS4wMjUtLjA4LS4xMDgtLjgxLTEuMTk4YTUyLjczOCA1Mi43Mz"
            + "ggMCAwIDAtLjA2Mi0uMWwtLjY3Mi0xLjEyNmEyNC4zNjMgMjQuMzYzIDAgMCAx"
            + "LTEuMjg1LTIuNjQ0bC0uMDM1LS4wODRjLS44Ny0yLjE0NS0xLjM0Ny00LjI4Mi"
            + "0xLjQzOC02LjM1NyAyLjAxMi0yLjgyIDUuMDU0LTQuNjY4IDguODIyLTUuMzN6"
            + "IiBmaWxsPSJ1c"
            + "mwoI20pIi8+PHBhdGggZD0iTTgwLjQyIDM2LjIwMmwtMy44Ni0uMDM0LTEuNjQ"
            + "zLjA2Ni4zMzMgNS45ODR6IiBmaWxsPSIjZmZmIi8+PHBhdGggZD0iTTU0LjE2"
            + "MiAxNy41OWMtMTEuNDA1LTcuNDAzLTI1LjA1My02LjYyNC0zMC40ODQgMS43NC0"
            + "xLjcyNCAyLjY1OC0yLjQxIDUuNzYxLTIuMTggOC45OTUgNS4wMS03Ljc5OCAxNy4"
            + "wNDgtOC40MzggMjcuMTM4LTEuMzUgOC42OTQgNi4xMDQgMTMuMTM1IDE1Ljk3OCA"
            + "xMS4yOTEgMjMuNzkgMi4wNDQtMS4xMTggMy43NzctMi42NTkgNS4wNS00LjYyMy"
            + "A1LjQzMy04LjM2NS41ODktMjEuMTQ5LTEwLjgxNS0yOC41NTF6IiBmaWxsPSJ1c"
            + "mwoI24pIi8+PHBhdGggZD0iTTEwLjc2OCA1Mi41NEw4LjUxIDYwLjU1Nmw3Ljc2O"
            + "CA4Ljc4NSA3LjU3MS01LjQ4IDkuMjQzIDUuNjg1LjA5OCAxMC43NDcgOS44NzYgM"
            + "S45NjYgNS42OTgtNy41NjQuMzM3LTIuMjg1IDUuMjI5LS4xNzcgNS4zMSA4LjE2M"
            + "yA3LjM3NC0zLjkyNiA2LjUxNi03LjY4NS0uMDAyLS4wMDEuMTIzLS4wNjMtMy4yN"
            + "zQtNi4xNCAzLjgxNSAyLjAwNiA3LjEyNi03LjQ3MyAxLjA4OC04LjAxMS03LjA2O"
            + "C01LjI3Ni0uMDg5LTEuNjEgNS4wNDgtNi4xOTUtMy41ODctNy4yNzktNS45MDYtM"
            + "S4yNjUtNS4yNzQtNi45NiAyLjUzMS0zLjM3Ny02LjAxMi01LjU5LTQuNTM1IDIuM"
            + "jE0LTcuNTk1LTUuMTY4LS4xMDUtMy43OTgtOC4yMjktMy40OC"
            + "0xLjg5OCA0Ljk1OC"
            + "02Ljc1MS0xLjI2N0wyOS4yNDQuMzdsLTguMjA1IDEuMDI2LTEuMDUzIDguNS0xL"
            + "jgxNi44MTEtNy4xNzMtMy41ODctNC42MjYgNS4zNy0uOTU5IDguODY1IDQuNDk"
            + "2IDQuMTA0LTguMjIzLjIyMkwuMzUgMzQuNTE1bC45ODMgOS41MDggOS4wNDYuO"
            + "TMgMi42ODIgNS40NTR6bTU0LjIxLTYuNGMtNS40MyA4LjM2OC0xOS4wNzcgOS4x"
            + "NDgtMzAuNDgyIDEuNzQ0QzIzLjA5MiA0MC40NzkgMTguMjUgMjcuNjk3IDIz"
            + "LjY4IDE5LjMzYzUuNDMtOC4zNjUgMTkuMDc4LTkuMTQ0IDMwLjQ4My0xLjc0I"
            + "DExLjQwNCA3LjQwMyAxNi4yNDggMjAuMTg3IDEwLjgxNiAyOC41NXoiIGZpbG"
            + "w9InVybCgjbykiLz48cGF0aCBkPSJNNzQuODMgNDMuNzMybC0uNDE3LTcuNDc4"
            + "IDUuMjk4LS4yMDktMy41MzEtNy4xNjYtNS44MTYtMS4yNDUtNS4xOTMtNi44NT"
            + "QgMi40OTMtMy4zMjQtNS45Mi01LjUwNS00LjQ2NSAyLjE4Mi03LjQ3OC01LjA5"
            + "LS4xMDMtMy43MzgtOC4xMDMtMy40MjYtMS44NjggNC44OC02LjY0Ni0xLjI0NUw"
            + "yOS40NDYuOTQzIDIxLjc2MSAxLjk4bDMuNTMgNi4xMjctNi43NTEgMy4wMTMtNy4"
            + "wNjItMy41MzEtNC4yNTggNS4xOTIgNi41NDMgNC45ODYtMS43NjcgNy43OS05Lj"
            + "I0Mi41MTcgMS4wNCA5LjU1NyA5LjU1NC45MzQgNC43NzcgOS4yNDItNi43NSA2Lj"
            + "Q0IDguMjAzIDguODI2IDcuOTk3LTUuNTA0IDkuNzYzIDUuNzEyLjEwMyAxMC44"
            + "MDEgMTEuMjE3IDIuMDc4IDEuNDU0LTkuODY2IDkuNjU4LS4zMTMgNS42MDggOC4"
            + "yMDQgNy43OS0zLjk0NS00Ljk4NS05LjM0OCA1LjA4OC02LjMzNCA3LjQ3OCAzLj"
            + "czOSAxLjAzOC03LjM3NHpNNjQuNjI3IDQ2LjAxYy01LjM0NSA4"
            + "LjIzNS0xOC43ODQgOS4wMDMtMzAuMDExIDEuNzE0LTExLjIzLTcuMjktMTUuOT"
            + "k5LTE5Ljg3NS0xMC42NTItMjguMTEyIDUuMzQ2LTguMjM2IDE4Ljc4NC05LjA"
            + "wNCAzMC4wMTMtMS43MTUgMTEuMjI5IDcuMjkxIDE1Ljk5NyAxOS44NzYgMTAuN"
            + "jUgMjguMTEzeiIgZmlsbD0idXJsKCNwKSIvPjxwYXRoIGQ9Ik03NC44MyA0My43M"
            + "zJsLS40MTctNy40NzggNS4yOTgtLjIwOS0zLjUzMS03LjE2Ni01LjgxNi0xLjI"
            + "0NS01LjE5My02Ljg1NCAyLjQ5My0zLjMyNC01LjkyLTUuNTA1LTQuNDY1IDIu"
            + "MTgyLTcuNDc4LTUuMDktLjEwMy0zLjczOC04LjEwMy0zLjQyNi0xLjg2OCA0Ljg4"
            + "LTYuNjQ2LTEuMjQ1TDI5LjQ0Ni45NDMgMjEuNzYxIDEuOThsMy41MyA2LjEyNy0"
            + "2Ljc1MSAzLjAxMy03LjA2Mi0zLjUzMS00LjI1OCA1LjE5MiA2LjU0MyA0Ljk4"
            + "Ni0xLjc2NyA3Ljc5LTkuMjQyLjUxNyAxLjA0IDkuNTU3IDkuNTU0LjkzNCA0Ljc"
            + "3NyA5LjI0Mi02Ljc1IDYuNDQgOC4yMDMgOC44MjYgNy45OTctNS41MDQgOS43NjM"
            + "gNS43MTIuMTAzIDEwLjgwMSAxMS4yMTcgMi4wNzggMS40NTQtOS44NjYgOS42NTg"
            + "tLjMxMyA1LjYwOCA4LjIwNCA3Ljc5LTMuOTQ1LTQuOTg1LTkuMzQ4IDUuMDg4LTY"
            + "uMzM0IDcuNDc4IDMuNzM5IDEuMDM4LTcuMzc0ek02NC42MjcgNDYuMDFjLTUuMzQ"
            + "1IDguMjM1LTE4Ljc4NCA5LjAwMy0zMC4wMTEgMS43MTQtMTEuMjMtNy4yOS0xNS"
            + "45OTktMTkuODc1LTEwLjY1Mi0yOC4xMTIgNS4zNDYtOC4yMzYgMTguNzg0LTk"
            + "uMDA0IDMwLjAxMy0xLjcxNSAxMS4yMjkgNy4yOTEgMTUuOTk3IDE5Ljg3NiAxMC"
            + "42NSAyOC4xMTN6IiBmaWxsPSJ1cmwoI3EpIi8+PHBhdGggZD0iTTI5LjIuMDI0"
            + "bC04LjQ3MyAxLjA2MS0xLjA2MSA4LjU3My0xLjQ4OC42NjQtNy4yNjktMy42Mz"
            + "QtNC44NzMgNS42NTgtLjk5IDkuMTQ3IDMuOTkxIDMuNjQyLTcuNjUyLjIwNkww"
            + "IDM0LjUwN2wxLjAxNyA5LjgzMiA5LjEzNS45MzljLjE1Mi4zMSAyLjI4MiA0Lj"
            + "Y0MiAyLjQ4NCA1LjA1bC0yLjE3NSAyLjAyNC0yLjMzNSA4LjI5MiA4LjEgOS4x"
            + "NjIgNy42MzgtNS41MjYgOC44ODMgNS40Ni4wOTkgMTAuODM4IDEwLjM2NyAyLj"
            + "A2NCA1Ljg4Mi03Ljgxcy4yNDUtMS42NDcuMzA4LTIuMDg2bDQuNzQzLS4xNiA1"
            + "LjM3OCA4LjI2NSA3LjcxMy00LjEwNiA2LjU1OC03LjczNy0uMDkzLjA3Ny4yNT"
            + "YtLjUyNy0yLjc0My01LjE0NSAzLjA0OCAxLjYwMSA3LjM4NS03Ljc0MSAxLjEz"
            + "LTguMzI2LTcuMS01LjNjLS4wMTQtLjIzOS0uMDYzLTEuMTE0LS4wNzMtMS4zMT"
            + "QuMTY0LS4yMDMgNS4xMDQtNi4yNjUgNS4xMDQtNi4yNjVsLTMuNzYtNy42My01"
            + "Ljk0NS0xLjI3My01LjAzOC02LjY1IDIuNTYyLTMuNDE2LTYuNDE2LTUuOTY2LT"
            + "QuNTY4IDIuMjMxLTcuMjgxLTQuOTU0Yy0uMDEtLjMyMy0uMTA3LTMuODQ0LS4x"
            + "MDctMy44NDRMNDEuMzkyLjg1OGwtMS45MjQgNS4wMjQtNi4zNC0xLjE5TDI5Lj"
            + "M5NyAwem0tLjEwNi43MTNsMy42NSA0LjU5IDcuMTYzIDEuMzQ0IDEuODc0LTQu"
            + "ODkzIDcuNjkxIDMuMjU0Yy4wMTIuMzk1LjEwNCAzLjc1Mi4xMDQgMy43NTJsNy"
            + "45MSA1LjM4MSA0LjUwMS0yLjE5OCA1LjYwOSA1LjIxNi0yLjUwMSAzLjMzNSA1"
            + "LjUxIDcuMjczIDUuODY4IDEuMjU2IDMuNDE0IDYuOTI4LTQuOTkxIDYuMTI4Lj"
            + "EwNSAxLjkwNiA3LjAzMyA1LjI1LTEuMDQ1IDcuNjk5LTYuODY4IDcuMTk5YTgx"
            + "OC43MiA4MTguNzIgMCAwIDEtNC4zNy0yLjQxNnMzLjIwMiA2LjQxIDMuNDU2ID"
            + "YuODg1bC02LjQxNCA3LjU2OGMtLjExMS4wNi02LjUyIDMuNDctNy4wMzYgMy43"
            + "NDZsLTUuMjQzLTguMDYtNS43MTQuMTkyLS4zNjcgMi40ODRjLS4xMDIuMTM2LT"
            + "UuMjY1IDYuOTktNS41MTIgNy4zMmwtOS4zODctMS44NjgtLjA5Ni0xMC42NTgt"
            + "OS42MDYtNS45MDQtNy41MDMgNS40MjhjLS4zODgtLjQ0LTcuMTk3LTguMTQtNy"
            + "40MzQtOC40MDYuMDk4LS4zNDQgMi4xMy03LjU2MiAyLjE4LTcuNzRsMi40MTEt"
            + "Mi4yNDItMi44OC01Ljg2LTguOTU2LS45Mi0uOTUtOS4xODIgMS4yODYtOC41MD"
            + "QgOC43OTMtLjIzNy01LjAwMy00LjU2NWMuMDM3LS4zMzIuOTA2LTguMzc2Ljky"
            + "OS04LjU4MS4xMy0uMTUzIDQuMDU4LTQuNzEyIDQuMzc4LTUuMDg1bDcuMDc5ID"
            + "MuNTQgMi4xNDYtLjk2IDEuMDQzLTguNDI0IDcuNzQzLS45N3oiIGZpbGw9InVy"
            + "bCgjcikiLz48cGF0aCBkPSJNMzQuMDUgNDguNTk3Yy02LjY3OS00LjMzNC0xMS"
            + "41LTEwLjg0My0xMi44OTYtMTcuNDA4LS45Ni00LjUxMi0uMjktOC43MTEgMS45"
            + "MzgtMTIuMTQzIDIuMjI4LTMuNDMzIDUuNzktNS43NTUgMTAuMzAzLTYuNzE0ID"
            + "YuNTY2LTEuMzk3IDE0LjQ3My4zNTcgMjEuMTUgNC42OTNhMzMuMDAxIDMzLjAw"
            + "MSAwIDAgMSA2LjUwMSA1LjUyNSA1My43MzIgNTMuNzMyIDAgMCAwIDIuNjM4LT"
            + "cuMzczbC0yLjEyNS0xLjk3Ni0zLjgyMyAxLjg2OGExLjA0MyAxLjA0MyAwIDAg"
            + "MS0xLjA0My0uMDc1bC03LjQ3OC01LjA4OWExLjA0IDEuMDQgMCAwIDEtLjQ1NS"
            + "0uODMybC0uMDg1LTMuMDctNi40OTMtMi43NDUtMS40ODMgMy44NzNjLS4xOC40"
            + "Ny0uNjcuNzQ0LTEuMTY1LjY1MmwtNi42NDYtMS4yNDdhMS4wMzcgMS4wMzcgMC"
            + "AwIDEtLjYyMi0uMzc1bC0zLjI2Ny00LjEwOC01LjU2Mi43NTIgMi43NTYgNC43"
            + "ODNhMS4wMzggMS4wMzggMCAwIDEtLjQ3NyAxLjQ3bC02Ljc1IDMuMDEzYTEuMD"
            + "QgMS4wNCAwIDAgMS0uODktLjAxOWwtNi4zMTgtMy4xNi0zLjA0OCAzLjcxNSA1"
            + "LjY4NSA0LjMzMmExLjA0MiAxLjA0MiAwIDAgMSAuMzg2IDEuMDZsLTEuNzY3ID"
            + "cuNzg5YTEuMDQgMS4wNCAwIDAgMS0uOTU4LjgxbC04LjE0Ny40NTYuODI5IDcu"
            + "NjI0YzEuNDAxLjEzNiA4LjcxMi44NTIgOC43MTIuODUyLjM1Mi4wMzQuNjYxLj"
            + "I0NC44MjQuNTU4bDQuNzc3IDkuMjQzYTEuMDQxIDEuMDQxIDAgMCAxLS4yMDYg"
            + "MS4yMzJsLTYuMDA2IDUuNzMgMi43ODkgM2E1Ni41ODUgNTYuNTg1IDAgMCAwID"
            + "E5Ljc5Ni01Ljg1M2MtLjQ2NC0uMjctLjkyMy0uNTQ5LTEuMzc1LS44NDN6IiBm"
            + "aWxsPSJ1cmwoI3MpIi8"
            + "+PC9wYXR0ZXJuPjwvZGVmcz48bGluZWFyR3JhZGllbnQgaWQ9InQiPjxzdG9wI"
            + "G9mZnNldD0iMCIgc3RvcC1jb2xvcj0iIzAwM2RjNyIvPjxzdG9wIG9mZnNldD0"
            + "iMSIgc3RvcC1jb2xvcj0iIzRjYTRmZiIvPjwvbGluZWFyR3JhZGllbnQ+PGxpbm"
            + "VhckdyYWRpZW50IGlkPSJ1IiB4MT0iNjMuMjcxIiB4Mj0iNjMuMjcxIiB5MT0iM"
            + "zQuNTg2IiB5Mj0iNzEuNTg0IiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVz"
            + "ZSIgeGxpbms6aHJlZj0iI3QiIGdyYWRpZW50VHJhbnNmb3JtPSJtYXRyaXgoLjY"
            + "5NDMyIDAgMCAuNjk0MzIgMzYuMzQgMzkuMTI3KSIvPjxsaW5lYXJHc"
            + "mFkaWVudCBpZD0iaCIgeDE9IjY4LjI1MSIgeDI9IjY4LjI1MSIgeTE9Ii0zLjQxO"
            + "CIgeTI9IjU4LjA4MyIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiP"
            + "jxzdG9wIG9mZnNldD0iMCIgc3RvcC1jb2xvcj0iIzAwMDA1MSIvPjxzdG9wI"
            + "G9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzU4YjFmZiIvPjwvbGluZWFyR3JhZGl"
            + "lbnQ+PHJhZGlhbEdyYWRpZW50IGlkPSJpIiBjeD0iNzMuNDI3IiBjeT0iMTM2Lj"
            + "c4NCIgcj0iNTMuMTUyIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+P"
            + "HN0b3Agb2Zmc2V0PSIwIiBzdG9wLWNvbG9yPSIjZmZmIi8+PHN0b3Agb2Zmc2V0"
            + "PSIxIiBzdG9wLWNvbG9yPSIjMDA2YWNhIi8+PC9yYWRpYWxHcmFkaWVudD48cmF"
            + "kaWFsR3JhZGllbnQgaWQ9ImoiIGN4PSI4MC42NzEiIGN5PSIxMjIuNjg2IiByPS"
            + "IxMTYuNzgxIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+PHN0b3Agb"
            + "2Zmc2V0PSIwIiBzdG9wLWNvbG9yPSIjY2FmZmZmIi8+PHN0b3Agb2Zmc2V0PSIx"
            + "IiBzdG9wLWNvbG9yPSIjMDA5MGZmIi8+PC9yYWRpYWxHcmFkaWVudD48cmFkaWFs"
            + "R3JhZGllbnQgaWQ9ImsiIGN4PSI2OS45NDgiIGN5PSIxMjAuNTg4IiByPSI5NS40"
            + "OTgiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIj48c3RvcCBvZmZzZXQ9"
            + "IjAiIHN0b3AtY29sb3I9IiNmZmYiLz48c3RvcCBvZmZzZXQ9IjEiIHN0b3AtY29s"
            + "b3I9IiMwMDk1ZmYiLz48L3JhZGlhbEdyYWRpZW50PjxsaW5lYXJHcmFkaWVudCBp"
            + "ZD0ieiIgeDE9IjYzLjk5OSIgeDI9IjYzLjk5OSIgeTE9IjU5LjA4NCIgeTI9IjE0"
            + "NS4wODIiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4bGluazpocmVm"
            + "PSIjdCIgZ3JhZGllbnRUcmFuc2Zvcm09Im1hdHJpeCguNjk0MzIgMCAwIC42OTQz"
            + "MiAzNi4zNCAzOS4xMjcpIi8+PHJhZGlhbEdyYWRpZW50IGlkPSJsIiBjeD0iLTEx"
            + "LjA0MyIgY3k9Ii0xMS4zNzYiIHI9IjEwMy40MyIgZ3JhZGllbnRVbml0cz0idXNl"
            + "clNwYWNlT25Vc2UiPjxzdG9wIG9mZnNldD0iMCIgc3RvcC1jb2xvcj0iI2ZmZiIv"
            + "PjxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzhiY2VmZiIvPjwvcmFkaWFs"
            + "R3JhZGllbnQ+PHBhdGggZD0iTTcwLjI2MyA2NC41NDNjLTMuOTk2LjcwMy03LjI"
            + "zIDIuNjc1LTkuMzQ5IDUuNzA0bC0uMDY3LjA5NS4wMDYuMTE4Yy4wODEgMi4xOT"
            + "IuNTg3IDQuNDUgMS41MDQgNi43MWwuMDM1LjA4NGMuMzU0Ljg2Mi43NzIgMS43M"
            + "yAxLjI0MyAyLjU4MWwuMDU1LjA5Ny43MTYgMS4yMDQuMDY1LjEwMi44MyAxLjIy"
            + "OS4wODEuMTEuNzk5IDEuMDQ5LjE4OC4yMzVhMzAuMTMxIDMwLjEzMSAwIDAgMCA"
            + "yLjI0NSAyLjQ2N2MwIC4wMDIuODk1Ljg1Ljg5NS44NWwuMjMyLjIxNCAxLjE1My"
            + "45ODMuMjU1LjIwMS45OTIuNzYxLjM0Ny4yNTMgMS4yOTUuODkxIDEuNDg0LjkwM"
            + "i4zNDMuMTk4IDEuNDgxLjc4Ny4xODguMDkgMS4zNjguNjI4LjMyNi4xNDEgMS41"
            + "LjU4Ni4yNzYuMDk3LS4wMDItLjAwMiAxLjM1OC40NDEuMjU4LjA3NiAxLjUxMy4"
            + "zOTIuMzEuMDY4IDEuNDI2LjI3Ni4xMjQuMDIxIDEuNTE4LjE5OC4zLjAyOWMxLj"
            + "AwNy4wOSAyLjAxNS4xMjIgMi45OTUuMDlsLjI2LS4wMDggMS40NTYtLjEwNy4wO"
            + "S0uMDEgMS4zNTgtLjIwMWMuMDAzLS4wMDIuMTk3LS4wMzYuMTk3LS4wMzZsMS4z"
            + "OC0uMzEzLjE0LS4wNCAxLjI1OC0uMzk4LjExLS4wMzUgMS4yNjMtLjUyMS4xMDc"
            + "tLjA1Yy4wNjYtLjAzMSAxLjI2LS42NTMgMS4yNi0uNjUzbC4xNDUtLjA3Ni4wMi"
            + "0uMTUyYzEuNjY2LTguMDIzLTMuMDQ2LTE3Ljc1MS0xMS40Ni0yMy42Ni01LjY2N"
            + "y0zLjk4NC0xMi4xODItNS42OTUtMTcuODctNC42OTZ6bS4xMi42ODFjNS41MDgt"
            + "Ljk2OSAxMS44MzQuNzAyIDE3LjM1NiA0LjU4MSA3LjEgNC45ODYgMTEuNSAxMi4"
            + "3MzggMTEuNSAxOS43NjYgMCAxLjAzMi0uMTExIDIuMDQ2LS4zMDYgMy4wMzZsLS"
            + "45OTIuNTE3LS4xNjQuMDc3LTEuMjEyLjQ5OC0uMTA1LjAzNi0xLjIxMS4zODItL"
            + "jE0Ny4wNDItMS4zMi4zLS4xOTMuMDM0LTEuMzEzLjE5NGEzLjU0MyAzLjU0MyAw"
            + "IDAgMC0uMDc5LjAxbC0xLjQxNS4xMDQtLjI1My4wMDhjLS45NTEuMDMxLTEuOTMy"
            + "IDAtMi45MTEtLjA4OGwtLjI5LS4wMjctMS40NzktLjE5My0uMTIzLS4wMjEtMS4z"
            + "OS0uMjdjLS4wMDMtLjAwMi0uMzAzLS4wNjctLjMwMy0uMDY3bC0xLjQ3NC0uMzgx"
            + "LS4yNTYtLjA3Ni0xLjMyNS0uNDMtLjI3LS4wOTMtMS40NjYtLjU3My0uMzE5LS4"
            + "xMzgtMS4zMzgtLjYxNS0uMTg1LS4wOS0xLjQ1LS43Ny0uMzEtLjE3Ny0xLjQ3OC0"
            + "uOS0xLjI2OC0uODcxLS4zNDItLjI0OC0uOTctLjc0NC0uMjUtLjE5Ny0xLjEyOC"
            + "0uOTY0LS4yMjYtLjIwOC0uODc2LS44MzEtLjI1Ni0uMjU0YTI5LjkwNCAyOS45M"
            + "DQgMCAwIDEtMS45MzktMi4xNTZsLS4xODQtLjIzMS0uNzgtMS4wMjRjLS4wNDIt"
            + "LjA1OS0uMDgtLjEwOS0uMDgtLjEwOWwtLjgxLTEuMTk3YTU2LjQ4MiA1Ni40ODI"
            + "gMCAwIDAtLjA2My0uMTAybC0uNjcyLTEuMTI1YTI0LjM2MyAyNC4zNjMgMCAwI"
            + "DEtMS4yODUtMi42NDRMNjMgNzYuOTFjLS44Ny0yLjE0NS0xLjM0Ny00LjI4Mi0"
            + "xLjQzOC02LjM1NyAyLjAxMi0yLjgyIDUuMDU0LTQuNjY4IDguODIyLTUuMzN6Ii"
            + "BmaWxsPSJ1cmwoI3UpIi8+PHBhdGggZD0iTTExOS44MDYgNzguNDQ0bC0zLjg1O"
            + "S0uMDM0LTEuNjQzLjA2Ni4zMzMgNS45ODR6IiBmaWxsPSIjZmZmIi8+PHBhdGgg"
            + "ZD0iTTkzLjU0OCA1OS44MzNjLTExLjQwNC03LjQwNC0yNS4wNTMtNi42MjUtMzA"
            + "uNDgzIDEuNzQtMS43MjUgMi42NTctMi40MSA1Ljc2LTIuMTggOC45OTQgNS4wM"
            + "S03Ljc5OCAxNy4wNDgtOC40MzggMjcuMTM3LTEuMzUgOC42OTUgNi4xMDQgMTM"
            + "uMTM1IDE1Ljk3OCAxMS4yOTIgMjMuNzkgMi4wNDMtMS4xMTggMy43NzYtMi42N"
            + "TggNS4wNS00LjYyMyA1LjQzMy04LjM2NS41ODktMjEuMTQ5LTEwLjgxNi0yOC4"
            + "1NTF6IiBmaWxsPSJ1cmwoI3YpIi8+PHBhdGggZD0iTTUwLjE1NSA5NC43ODJsL"
            + "TIuMjU4IDguMDE2IDcuNzY4IDguNzg1IDcuNTctNS40NzkgOS4yNDQgNS42ODQ"
            + "uMDk4IDEwLjc0NyA5Ljg3NiAxLjk2NiA1LjY5OC03LjU2NC4zMzctMi4yODUgN"
            + "S4yMjgtLjE3NyA1LjMxIDguMTYzIDcuMzc1LTMuOTI2IDYuNTE1LTcuNjg1LjE"
            + "yMi0uMDYzLTMuMjczLTYuMTQgMy44MTQgMi4wMDUgNy4xMjYtNy40NzIgMS4wO"
            + "DgtOC4wMTItNy4wNjgtNS4yNzUtLjA4OC0xLjYxIDUuMDQ3LTYuMTk2LTMuNTg"
            + "3LTcuMjc4LTUuOTA2LTEuMjY1LTUuMjc0LTYuOTYxIDIuNTMxLTMuMzc2LTYuM"
            + "DEyLTUuNTktNC41MzUgMi4yMTMtNy41OTUtNS4xNjctLjEwNS0zLjc5OC04LjI"
            + "yOC0zLjQ4MS0xLjg5OSA0Ljk1OC02Ljc1MS0xLjI2Ni0zLjY5Mi00LjY0MS04L"
            + "jIwNSAxLjAyNy0xLjA1MyA4LjQ5OS0xLjgxNi44MTItNy4xNzMtMy41ODgtNC4"
            + "2MjYgNS4zNzEtLjk1OSA4Ljg2NSA0LjQ5NiA0LjEwMy04LjIyMy4yMjItMS4zM"
            + "zUgOC44MzUuOTgzIDkuNTA4IDkuMDQ2LjkyOSAyLjY4MyA1LjQ1NXptNTQuMjE"
            + "tNi4zOTljLTUuNDMgOC4zNjctMTkuMDc4IDkuMTQ3LTMwLjQ4MiAxLjc0My0xM"
            + "S40MDUtNy40MDQtMTYuMjQ3LTIwLjE4Ny0xMC44MTctMjguNTU0IDUuNDMtOC4"
            + "zNjUgMTkuMDc4LTkuMTQ0IDMwLjQ4Mi0xLjc0IDExLjQwNSA3LjQwMyAxNi4yN"
            + "DkgMjAuMTg3IDEwLjgxNiAyOC41NTF6IiBmaWxsPSJ1cmwoI3cpIi8+PHBhdGg"
            + "gZD0iTTExNC4yMTYgODUuOTc0bC0uNDE2LTcuNDc3IDUuMjk4LS4yMS0zLjUzM"
            + "S03LjE2NS01LjgxNi0xLjI0Ni01LjE5My02Ljg1NCAyLjQ5My0zLjMyNC01Ljk"
            + "yLTUuNTA1LTQuNDY1IDIuMTgyLTcuNDc5LTUuMDktLjEwMi0zLjczOC04LjEwM"
            + "y0zLjQyNi0xLjg2OCA0Ljg4LTYuNjQ2LTEuMjQ1LTMuNjM1LTQuNTcxLTcuNjg"
            + "2IDEuMDM4IDMuNTMgNi4xMjctNi43NSAzLjAxMy03LjA2Mi0zLjUzMS00LjI1O"
            + "CA1LjE5MiA2L"
            + "jU0MyA0Ljk4Ni0xLjc2NyA3Ljc5LTkuMjQzLjUxNyAxLjA0IDkuNTU3IDkuNTU0"
            + "LjkzNCA0Ljc3OCA5LjI0Mi02Ljc1IDYuNDQgOC4yMDMgOC44MjYgNy45OTctNS"
            + "41MDQgOS43NjMgNS43MTIuMTAzIDEwLjgwMSAxMS4yMTcgMi4wNzggMS40NTMt"
            + "OS44NjYgOS42Ni0uMzEzIDUuNjA3IDguMjA0IDcuNzktMy45NDUtNC45ODYtOS4z"
            + "NDggNS4wODgtNi4zMzQgNy40NzkgMy43NCAxLjAzOC03LjM3NXptLTEwLjIwMSAy"
            + "LjI3OGMtNS4zNDUgOC4yMzUtMTguNzg0IDkuMDAzLTMwLjAxMiAxLjcxNC0xMS4y"
            + "My03LjI4OS0xNS45OTgtMTkuODc1LTEwLjY1MS0yOC4xMTEgNS4zNDYtOC4yMzc"
            + "gMTguNzg0LTkuMDA1IDMwLjAxMi0xLjcxNSAxMS4yMyA3LjI5IDE1Ljk5NyAxOS"
            + "44NzUgMTAuNjUgMjguMTEyeiIgZmlsbD0idXJsKCN4KSIvPjxwY"
            + "XRoIGQ9Ik0xMTQ"
            + "uMjE2IDg1Ljk3NGwtLjQxNi03LjQ3NyA1LjI5OC0uMjEtMy41MzEtNy4xNjUtNS"
            + "44MTYtMS4yNDYtNS4xOTMtNi44NTQgMi40OTMtMy4zMjQtNS45Mi01LjUwN"
            + "S00LjQ2NSAyLjE4Mi03LjQ3OS01LjA5LS4xMDItMy43MzgtOC4xMDMtMy40MjY"
            + "tMS44NjggNC44OC02LjY0Ni0xLjI0NS0zLjYzNS00LjU3MS03LjY4NiAxLjAzO"
            + "CAzLjUzIDYuMTI3LTYuNzUgMy4wMTMtNy4wNjItMy41MzEtNC4yNTggNS4xOT"
            + "IgNi41NDMgNC45ODYtMS43NjcgNy43OS05LjI0My41MTcgMS4wNCA5LjU1NyA"
            + "5LjU1NC45MzQgNC43NzggOS4yNDItNi43NSA2LjQ0IDguMjAzIDguODI2IDcu"
            + "OTk3LTUuNTA0IDkuNzYzIDUuNzEyLjEwMyAxMC44MDEgMTEuMjE3IDIuMDc4I"
            + "DEuNDUzLTkuODY2IDkuNjYtLjMxMyA1LjYwNyA4LjIwNCA3Ljc5LTMuOTQ1LT"
            + "QuOTg2LTkuMzQ4IDUuMDg4LTYuMzM0IDcuNDc5IDMuNzQgMS4wMzgtNy4zNzV"
            + "6bS0xMC4yMDEgMi4yNzhjLTUuMzQ1IDgu"
            + "MjM1LTE4Ljc4NCA5LjAwMy0zMC4wMTIgMS43MTQtMTEuMjMtNy4yODktMTUuOTk"
            + "4LTE5Ljg3NS0xMC42NTEtMjguMTExIDUuMzQ2LTguMjM3IDE4Ljc4NC05LjAwNSA"
            + "zMC4wMTItMS43MTUgMTEuMjMgNy4yOSAxNS45OTcgMTkuODc1IDEwLjY1IDI4Lj"
            + "ExMnoiIGZpbGw9InVybCgjeSkiLz48cGF0aCBkPSJNNjguNTg4IDQyLjI2Nmw"
            + "tOC40NzQgMS4wNjEtMS4wNjEgOC41NzMtMS40ODguNjY1LTcuMjctMy42MzUt"
            + "NC44NzIgNS42NTgtLjk5IDkuMTQ3IDMuOTkxIDMuNjQyLTcuNjUyLjIwNi0xLjM"
            + "4NSA5LjE2NiAxLjAxNyA5LjgzMiA5LjEzNC45MzkgMi40ODUgNS4wNTEtMi4xN"
            + "zYgMi4wMjMtMi4zMzUgOC4yOTMgOC4xMDIgOS4xNjEgNy42MzctNS41MjYgOC4"
            + "4ODMgNS40Ni4wOTkgMTAuODM4IDEwLjM2NyAyLjA2NSA1Ljg4Mi03LjgxLjMw"
            + "OC0yLjA4NyA0Ljc0My0uMTYgNS4zNzggOC4yNjUgNy43MTMtNC4xMDYgNi41NT"
            + "gtNy43MzctLjA5My4wNzcuMjU2LS41MjctMi43NDMtNS4xNDUgMy4wNDggMS42M"
            + "DIgNy4zODQtNy43NDIgMS4xMzEtOC4zMjYtNy4xMDEtNS4zLS4wNzItMS4zMTQg"
            + "NS4xMDQtNi4yNjUtMy43Ni03LjYzLTUuOTQ1LTEuMjczLTUuMDM4LTYuNjUgMi4"
            + "1NjItMy40MTYtNi40MTYtNS45NjUtNC41NjkgMi4yMy03LjI4LTQuOTU0Yy0uMD"
            + "EtLjMyMi0uMTA3LTMuODQ0LS4xMDctMy44NDRMODAuNzc5IDQzLjFzLTEuNzQgN"
            + "C41NC0xLjkyNCA1LjAyNGMtLjUxMi0uMDk2LTYuMTA2LTEuMTQ1LTYuMzQtMS4x"
            + "OWwtMy43MzItNC42OTJ6bS0uMTA3LjcxNGwzLjY1IDQuNTg5IDcuMTYzIDEuMzQ"
            + "0IDEuODc0LTQuODkzIDcuNjkxIDMuMjU1Yy4wMTIuMzk1LjEwNCAzLjc1Mi4xMD"
            + "QgMy43NTJsNy45MSA1LjM4IDQuNTAxLTIuMTk4IDUuNjA5IDUuMjE2LTIuNTAxI"
            + "DMuMzM1IDUuNTEgNy4yNzMgNS44NjggMS4yNTdjLjEzOC4yOCAzLjIzIDYuNTU3"
            + "IDMuNDE0IDYuOTI3bC00Ljk5MSA2LjEyOC4xMDUgMS45MDYgNy4wMzMgNS4yNS0"
            + "xLjA0NiA3LjY5OS02Ljg2NyA3LjJjLS40MS0uMjE1LTQuMzctMi40MTYtNC4zNy"
            + "0yLjQxNnMzLjIwMiA2LjQxIDMuNDU1IDYuODg0bC02LjQxNCA3LjU2OGMtLjExL"
            + "jA2LTYuNTIgMy40Ny03LjAzNSAzLjc0NmwtNS4yNDQtOC4wNi01LjcxMy4xOTIt"
            + "LjM2NyAyLjQ4NS01LjUxMyA3LjMyLTkuMzg2LTEuODY5LS4wOTctMTAuNjU3LTk"
            + "uNjA1LTUuOTA1LTcuNTAzIDUuNDI4LTcuNDM0LTguNDA2Yy4wOTgtLjM0NCAyLj"
            + "EzLTcuNTYyIDIuMTgtNy43NGwyLjQxMS0yLjI0Mi0yLjg4LTUuODYtOC45NT"
            + "ctLjkyLS45NS05LjE4MiAxLjI4Ny04LjUwNCA4Ljc5Mi0uMjM3LTUuMDAyLT"
            + "QuNTY1Yy4wMzctLjMzMi45MDYtOC4zNzYuOTI5LTguNTgxLjEzLS4xNTMgNC4"
            + "wNTctNC43MTIgNC4zNzgtNS4wODVsNy4wNzkgMy41NCAyLjE0Ni0uOTU5I"
            + "DEuMDQzLTguNDI1IDcuNzQzLS45N3oiIGZpbGw9InVybCgjeikiLz48cGF"
            + "0aCBkPSJNNzMuNDM2IDkwLjg0Yy02LjY3OC00LjMzNC0xMS41LTEwLjg0N"
            + "C0xMi44OTUtMTcuNDA4LS45Ni00LjUxMy0uMjktOC43MTIgMS45MzctMTI"
            + "uMTQ0IDIuMjI4LTMuNDMzIDUuNzkxLTUuNzU1IDEwLjMwNC02L"
            + "jcxNCA2LjU2Ni0xLjM5NyAxNC40NzIuMzU3IDIxLjE1"
            + "IDQuNjkzYTMzLjAwMSAzMy4wMDEgMCAwIDEgNi41MDEgNS41MjUgNT"
            + "MuNzMyIDUzL"
            + "jczMiAwIDAgMCAyLjYzOC03LjM3M2wtMi4xMjUtMS45NzYtMy44"
            + "MjMgMS44NjhhMS"
            + "4wNDMgMS4wNDMgMCAwIDEtMS4wNDMtLjA3NGwtNy40NzktNS4wOWExLjA0IDEuM"
            + "DQgMCAwIDEtLjQ1NC0uODMybC0uMDg1LTMuMDctNi40OTMtMi43NDUtMS40ODMg"
            + "My44NzNjLS4xOC40Ny0uNjcuNzQ0LTEuMTY1LjY1MmwtNi42NDYtMS4yNDdhMS4"
            + "wMzcgMS4wMzcgMCAwIDEtLjYyMy0uMzc1bC0zLjI2Ni00LjEwOC01LjU2Mi43NT"
            + "JhMjMxNzYuNSAyMzE3Ni41IDAgMCAwIDIuNzU2IDQuNzgzIDEuMDM4IDEuMD"
            + "M4IDAgMCAxLS40NzcgMS40N2wtNi43NSAzLjAxNGExLjA0IDEuMDQgMCAwIDE"
            + "tLjg5LS4wMmwtNi4zMTktMy4xNTktMy4wNDcgMy43MTUgNS42ODUgNC4zMzFhM"
            + "S4wNDIgMS4wNDIgMCAwIDEgLjM4NSAxLjA2TDUyLjQgNjguMDNhMS4wNCAxLjA"
            + "0IDAgMCAxLS45NTcuODFsLTguMTQ3LjQ1Ni44MjkgNy42MjQgOC43MTEuODUyY"
            + "y4zNTIuMDM0LjY2Mi4yNDQuODI1LjU1OGw0Ljc3NyA5LjI0M2ExLjA0MSAxLjA"
            + "0MSAwIDAgMS0uMjA2IDEuMjMybC02LjAwNiA1LjczIDIuNzg4IDNhNTYuNTg1I"
            + "DU2LjU4NSAwIDAgMCAxOS43OTctNS44NTNjLS40NjUtLjI3LS45MjMtLjU0OS0"
            + "xLjM3NS0uODQzeiIgZmlsbD0idXJsKCNBKSIvPjxwYXRoIGQ9Ik0xMjUuMjEyI"
            + "DEyOEgzNi4zMzlWMzkuMTI3aDg4Ljg3M3oiIGZpbGw9Im5vbmUiLz48cGF0aCB"
            + "maWxsPSJ1cmwoI0IpIiBkPSJNNS44MzUgMy4xMTVoODIuNzc5djgyLjY0Mkg1L"
            + "jgzNXoiLz48cGF0aCBkPSJNOTEuNjYgODguODczSDIuNzg5VjBoODguODczeiI"
            + "gZmlsbD0ibm9uZSIvPjwvc3ZnPg==";


}

