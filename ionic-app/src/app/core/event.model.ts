import {Column, Entity, PrimaryColumn} from 'typeorm';

@Entity('event')
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

  @Column({type: 'simple-array'})
  galleryImagesUrls: string[];
}
