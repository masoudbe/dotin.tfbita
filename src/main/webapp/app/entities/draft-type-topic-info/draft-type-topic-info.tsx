import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-type-topic-info.reducer';

export const DraftTypeTopicInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftTypeTopicInfoList = useAppSelector(state => state.draftTypeTopicInfo.entities);
  const loading = useAppSelector(state => state.draftTypeTopicInfo.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-type-topic-info-heading" data-cy="DraftTypeTopicInfoHeading">
        <Translate contentKey="tfbitaApp.draftTypeTopicInfo.home.title">Draft Type Topic Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftTypeTopicInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/draft-type-topic-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftTypeTopicInfo.home.createLabel">Create new Draft Type Topic Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftTypeTopicInfoList && draftTypeTopicInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('currencySellCommissionTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.currencySellCommissionTopic">
                    Currency Sell Commission Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencySellCommissionTopic')} />
                </th>
                <th className="hand" onClick={sort('documentReceiptDisciplinaryTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.documentReceiptDisciplinaryTopic">
                    Document Receipt Disciplinary Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('documentReceiptDisciplinaryTopic')} />
                </th>
                <th className="hand" onClick={sort('draftMainTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.draftMainTopic">Draft Main Topic</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftMainTopic')} />
                </th>
                <th className="hand" onClick={sort('insuranceCostTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.insuranceCostTopic">Insurance Cost Topic</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceCostTopic')} />
                </th>
                <th className="hand" onClick={sort('justificationDisciplinaryTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.justificationDisciplinaryTopic">
                    Justification Disciplinary Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('justificationDisciplinaryTopic')} />
                </th>
                <th className="hand" onClick={sort('openDraftDisciplinaryTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.openDraftDisciplinaryTopic">Open Draft Disciplinary Topic</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('openDraftDisciplinaryTopic')} />
                </th>
                <th className="hand" onClick={sort('otherCostsTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.otherCostsTopic">Other Costs Topic</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherCostsTopic')} />
                </th>
                <th className="hand" onClick={sort('postTelegraphSwiftCostsTopic')}>
                  <Translate contentKey="tfbitaApp.draftTypeTopicInfo.postTelegraphSwiftCostsTopic">
                    Post Telegraph Swift Costs Topic
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postTelegraphSwiftCostsTopic')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftTypeTopicInfoList.map((draftTypeTopicInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-type-topic-info/${draftTypeTopicInfo.id}`} color="link" size="sm">
                      {draftTypeTopicInfo.id}
                    </Button>
                  </td>
                  <td>{draftTypeTopicInfo.currencySellCommissionTopic}</td>
                  <td>{draftTypeTopicInfo.documentReceiptDisciplinaryTopic}</td>
                  <td>{draftTypeTopicInfo.draftMainTopic}</td>
                  <td>{draftTypeTopicInfo.insuranceCostTopic}</td>
                  <td>{draftTypeTopicInfo.justificationDisciplinaryTopic}</td>
                  <td>{draftTypeTopicInfo.openDraftDisciplinaryTopic}</td>
                  <td>{draftTypeTopicInfo.otherCostsTopic}</td>
                  <td>{draftTypeTopicInfo.postTelegraphSwiftCostsTopic}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-type-topic-info/${draftTypeTopicInfo.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/draft-type-topic-info/${draftTypeTopicInfo.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-type-topic-info/${draftTypeTopicInfo.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.home.notFound">No Draft Type Topic Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftTypeTopicInfo;
