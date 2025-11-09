package com.ExploreEase.ExploreEase.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


	@Entity
	@Table(name = "roles")
//	@Getter
//	@Setter
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Builder
	public class Role {

	    public Role() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Role(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public static class Builder {
	        private Long id;
	        private String name;

	        public Builder id(Long id) {
	            this.id = id;
	            return this;
	        }

	        public Builder name(String name) {
	            this.name = name;
	            return this;
	        }

	        public Role build() {
	            return new Role(id, name);
	        }
	    }

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String name;  // e.g., ROLE_USER, ROLE_ADMIN
	}

