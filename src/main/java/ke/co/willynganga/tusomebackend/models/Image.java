package ke.co.willynganga.tusomebackend.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "images_table")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  @NonNull
  @Lob
  byte[] image;
}
