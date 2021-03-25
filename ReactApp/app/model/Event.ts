import {Column, Entity, PrimaryColumn} from 'typeorm/browser';

@Entity()
export class Event {
  @PrimaryColumn()
  id: number;

  @Column()
  imageUrl: string;

  @Column()
  name: string;

  @Column()
  date: string;

  @Column()
  place: string;

  @Column()
  description: string;

  @Column()
  galleryImagesUrls: string;
}
