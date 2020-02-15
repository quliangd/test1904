package qu.com.Optional;

import java.util.Optional;

//注意：Optional 不能被序列化
public class NewMan {

	private Optional<Godness> godness = Optional.empty();

	public NewMan(Optional<Godness> godness) {
		this.godness = godness;
	}

	public NewMan() {
	}

	public Optional<Godness> getGodness() {
		return godness;
	}

	public void setGodness(Optional<Godness> godness) {
		this.godness = godness;
	}

	@Override
	public String toString() {
		return "NewMan{" +
				"godness=" + godness +
				'}';
	}
}
