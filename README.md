# ActiveMqConsomateur-

Configuration:

On copie les données dans lib de activeMq et les place dans WEB-INF/lib

Ensuite on a crée une interface ICons et on l'a implementé comme tel:


public class ConsImpl implements Icons{
	 private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	 private ConnectionFactory connectionFactory;
	 private Connection connection;
	 private Session session;
	 private  Destination destination;
	 private MessageConsumer consumer;
	 private Message message;
	public ConsImpl() throws JMSException {
		this.connectionFactory = new ActiveMQConnectionFactory(url);
		connection = connectionFactory.createConnection();
		
	}
	@Override
	public String getMessage(String subject) throws JMSException {
		 connection.start();
		 session = connection.createSession(false,
	                Session.AUTO_ACKNOWLEDGE);
		 destination = session.createQueue(subject);
		 consumer = session.createConsumer(destination);
		 message = consumer.receive();
		 String mess="";
		 if (message instanceof TextMessage) {
	             mess = ((TextMessage) message).getText().toString();
	            return mess;
	        }
	        connection.close();
		return mess;
	}

}

Ensuite dans le controller :


@WebServlet(name="conso",value="/conso")
public class ConsoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public Icons icons;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String objet = request.getParameter("objet");
		if(objet!=null ) {
			String message ="";
			ConsImpl cons = null;
			try {
				 cons = new ConsImpl();
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			 message =	cons.getMessage(objet);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(message!=null) {
			 request.setAttribute("message", message);
			 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); 
			
		}
		if(message==null) {
			 request.setAttribute("message", "Echec de recuperation de message !!!");
			 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); 
			
		}
		}
		
		}

}

Demarrage:

Il fonctionne avec Le producteur donc 
Il faut que activemq et le producteur soient demarrés et qu'on le demarre aussi 
