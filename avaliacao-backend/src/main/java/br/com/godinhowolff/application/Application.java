/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.godinhowolff.application;

import javax.persistence.Persistence;

import org.wso2.msf4j.MicroservicesRunner;

import br.com.godinhowolff.clientes.CadastroClientes;
import br.com.godinhowolff.model.Cliente;


/**
 * Application entry point
 *
 * @since 0.1-SNAPSHOT
 */
public class Application {
    public static void main(String[] args) {
        
    	int port=8080;
    	
    	if (args.length > 0 ) {
    		port = Integer.parseInt(args[0]);
    	}
    	
        new MicroservicesRunner(port).deploy(new CadastroClientes(getRepository())).start();
    }
    
    public static ClienteRepository getRepository() {
        return new ClienteRepository(Persistence.createEntityManagerFactory("clientes"));
    }
 }

