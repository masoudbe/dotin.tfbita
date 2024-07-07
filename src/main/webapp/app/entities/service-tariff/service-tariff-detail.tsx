import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './service-tariff.reducer';

export const ServiceTariffDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const serviceTariffEntity = useAppSelector(state => state.serviceTariff.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="serviceTariffDetailsHeading">
          <Translate contentKey="tfbitaApp.serviceTariff.detail.title">ServiceTariff</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{serviceTariffEntity.id}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.serviceTariff.code">Code</Translate>
            </span>
          </dt>
          <dd>{serviceTariffEntity.code}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="tfbitaApp.serviceTariff.title">Title</Translate>
            </span>
          </dt>
          <dd>{serviceTariffEntity.title}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.serviceTariff.draft">Draft</Translate>
          </dt>
          <dd>
            {serviceTariffEntity.drafts
              ? serviceTariffEntity.drafts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {serviceTariffEntity.drafts && i === serviceTariffEntity.drafts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/service-tariff" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/service-tariff/${serviceTariffEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ServiceTariffDetail;
