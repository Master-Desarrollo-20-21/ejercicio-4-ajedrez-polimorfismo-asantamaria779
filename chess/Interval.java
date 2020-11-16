package chess;

class Interval {

	private double inferior;

	private double superior;

	public Interval(double inferior, double superior) {
		this.inferior = inferior;
		this.superior = superior;
	}

	public Interval(double superior) {
		this(0, superior);
	}

	public Interval(Interval interval) {
		this(interval.inferior, interval.superior);
	}

	public Interval() {
		this(0, 0);
	}

	public Interval clone() {
		return new Interval(this);
	}

	public double longitud() {
		return superior - inferior;
	}

	public void desplazar(double desplazamiento) {
		inferior += desplazamiento;
		superior += desplazamiento;
	}

	public Interval desplazado(double desplazamiento) {
		Interval intervalo = this.clone();
		intervalo.desplazar(desplazamiento);
		return intervalo;
	}

	public boolean include(double valor) {
		return inferior <= valor && valor <= superior;
	}

	public boolean include(Interval interval) {
		assert interval!=null;
		return this.include(interval.inferior) && 
				this.include(interval.superior);
	}

	public boolean equals(Interval interval) {
		assert interval!=null;
		return inferior == interval.inferior && 
				superior == interval.superior;
	}

	public Interval interseccion(Interval interval) {
		assert this.intersecta(interval);
		if (this.include(interval)){
			return interval.clone();
		} else if (interval.include(this)){
			return this.clone();
		} else if (this.include(interval.inferior)){
			return new Interval(interval.inferior, superior);
		} else {
			return new Interval(inferior, interval.superior);
		}
	}

	public boolean intersecta(Interval interval) {
		assert interval!=null;
		return this.include(interval.inferior) ||
				this.include(interval.superior) || 
				interval.include(this);
	}

	public void oponer() {
		double inferiorInicial = inferior;
		double superiorInicial = superior;
		inferior = -superiorInicial;
		superior = -inferiorInicial;
	}

	public void doblar() {
		double longitudInicial = this.longitud();
		inferior -= longitudInicial / 2;
		superior += longitudInicial / 2;
	}

	public void recoger() {
		Console gestorIO = new Console();
		gestorIO.out("Inferior?");
		inferior = gestorIO.inDouble();
		gestorIO.out("Superior?");
		superior = gestorIO.inDouble();
	}

	public void mostrar() {
		new Console().out("[" + inferior + "," + superior + "]");
	}

	public Interval[] trocear(int trozos) {
		return null;
	}
	
	public static void main(String[] args){
		Interval interval = new Interval();
		interval.recoger();
		interval.mostrar();
		new Console().out("Longitud: "+interval.longitud());
	}

}
